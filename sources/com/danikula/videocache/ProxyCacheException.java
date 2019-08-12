package com.danikula.videocache;

public class ProxyCacheException extends Exception {
    public ProxyCacheException(String message) {
        super(message + ". Version: 2.7.0");
    }

    public ProxyCacheException(String message, Throwable cause) {
        super(message + ". Version: 2.7.0", cause);
    }
}
