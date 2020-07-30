package com.antoniosanzc.spring.boot.token.example.authentication.core;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;


public class RateLimiter {

	 private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);

	    private final int bucketCapacity;
	    private final AtomicInteger bucket;

	    @Autowired
	    public RateLimiter (@Value("${app.bucketCapacity:100}") int bucketCapacity) {
	        this.bucketCapacity = bucketCapacity;
	        bucket = new AtomicInteger(bucketCapacity);
	        log.info("initial capacity set to {}", bucketCapacity);
	    }

	    @Scheduled(fixedRateString = "${app.tokenSpawnTimeInMillis:100}")
	    public void reportCurrentTime() {
	        if (bucket.get() < bucketCapacity) {
	            bucket.incrementAndGet();
	        }
	    }

	    public synchronized boolean isRequestAcceptable (HttpServletRequest request)
	    {
	        if (bucket.get() == 0) {
	            return false;
	        } else {
	            bucket.decrementAndGet();
	            return true;
	        }
	    }
}
