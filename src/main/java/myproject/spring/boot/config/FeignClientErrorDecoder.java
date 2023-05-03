package myproject.spring.boot.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignClientErrorDecoder implements ErrorDecoder {
    private static final ErrorDecoder DEFAULT_ERROR_DECODER = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == HttpStatus.REQUEST_TIMEOUT.value() || response.status() >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new RetryableException(response.status(), response.reason(), response.request().httpMethod(), null, response.request());
        }

        return DEFAULT_ERROR_DECODER.decode(s, response);
    }
}

