package cn.phpst.DesignatedDriver.core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UnifyResponse {
    private int Code;
    private String Message;

    public static void success() {

    }
}