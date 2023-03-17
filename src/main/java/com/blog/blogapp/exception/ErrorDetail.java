package com.blog.blogapp.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetail {
    private Date timeStamp;
    private String message;
    private String details;

    public ErrorDetail(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public ErrorDetail() {

    }
}
