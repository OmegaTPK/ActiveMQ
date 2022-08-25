package com.example.ActiveMQ.exception;

import java.time.Instant;

public record ApiExceptionModel(String message, Instant timeStamp) {
}
