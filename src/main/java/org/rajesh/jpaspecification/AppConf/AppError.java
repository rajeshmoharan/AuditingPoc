package org.rajesh.jpaspecification.AppConf;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @Builder
public class AppError {
    private HttpStatus status;
    private String message;
}
