package myproject.spring.boot.config;

import java.util.concurrent.TimeUnit;

import feign.Retryer;


public class FeignClientRetryer extends Retryer.Default {
    private static final int MAX_ATTEMPTS = 3;

    public FeignClientRetryer() {
        super(100L, TimeUnit.SECONDS.toMillis(1L), MAX_ATTEMPTS);
    }
}

