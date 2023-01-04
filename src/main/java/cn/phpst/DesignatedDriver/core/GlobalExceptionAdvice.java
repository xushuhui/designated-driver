package cn.phpst.designatedDriver.core;

import cn.phpst.designatedDriver.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req,Exception e){

        return UnifyResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.toString()).
        reason(HttpStatus.INTERNAL_SERVER_ERROR.toString()).build();
    }
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req,HttpException e){
        UnifyResponse message = UnifyResponse.builder().code(e.getCode()).message(e.getMessage()).reason(e.getReason()).metadata(e.getMetadata()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getCode());
        return new ResponseEntity<>(message,headers,httpStatus);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UnifyResponse handBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        return UnifyResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message(message).reason("MethodArgumentNotValid").build();
    }

    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer buffer = new StringBuffer();
        errors.forEach(error -> buffer.append(error.getDefaultMessage()).append(';'));
        return buffer.toString();
    }
}