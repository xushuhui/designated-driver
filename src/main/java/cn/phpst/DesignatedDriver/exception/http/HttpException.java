package cn.phpst.DesignatedDriver.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException{
    protected int code;
    protected int httpStatus=HttpStatus.INTERNAL_SERVER_ERROR.value();
}
