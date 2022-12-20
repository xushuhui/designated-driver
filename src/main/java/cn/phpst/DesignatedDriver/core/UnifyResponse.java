package cn.phpst.DesignatedDriver.core;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class UnifyResponse {
    protected int code;
    protected String message;
    protected String reason;
    protected Object metadata;

}