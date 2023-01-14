package pro.sky.socksshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SockBadParamException extends RuntimeException {
    public SockBadParamException(String message) {
        super(message);
    }
}
