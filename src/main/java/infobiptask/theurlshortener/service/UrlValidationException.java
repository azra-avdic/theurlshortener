package infobiptask.theurlshortener.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UrlValidationException(String string) {
        super(string);
    }
}
