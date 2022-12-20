package cn.phpst.DesignatedDriver.exception.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpException extends RuntimeException{
    protected int code=HttpStatus.INTERNAL_SERVER_ERROR.value();
    protected String message;
    protected String reason;
    protected Object metadata;
}
