package com.example.ActiveMQ.exception;

import lombok.AllArgsConstructor;

import java.time.Instant;

public record ApiExceptionModel(String message, Instant timeStamp) {
}
