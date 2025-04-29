package org.rajesh.jpaspecification.AppConf;

import java.time.LocalDateTime;

public class ApiResponse <T>{

    private T data;

    private LocalDateTime timestamp;

    private AppError error;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(AppError error) {
        this();
        this.error = error;
    }
}
