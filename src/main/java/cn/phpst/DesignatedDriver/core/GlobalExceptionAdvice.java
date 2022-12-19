package cn.phpst.DesignatedDriver.core;

import cn.phpst.DesignatedDriver.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse resp = new UnifyResponse(9999, "服务器异常");
        return resp;
    }
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req,HttpException e){
        UnifyResponse message = new UnifyResponse(e.getCode(),e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatus());
        ResponseEntity<UnifyResponse> r  = new ResponseEntity<>(message,headers,httpStatus);
        return r;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UnifyResponse handBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        return new UnifyResponse(10001, message);
    }

    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer buffer = new StringBuffer();
        errors.forEach(error -> buffer.append(error.getDefaultMessage()).append(';')
        );
        return buffer.toString();
    }
}