package pro.sky.socksshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SockNotFoundException extends RuntimeException {
    public SockNotFoundException(String message) {
        super(message);
    }
}
