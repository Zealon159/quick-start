package cn.zealon.common.exception;

/**
 * @auther: Zealon
 * @Date: 2018-07-04 16:00
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
