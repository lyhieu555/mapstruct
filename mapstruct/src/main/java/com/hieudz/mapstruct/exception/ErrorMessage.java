package com.hieudz.mapstruct.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {

    private int statusCode;
    private String message;
}
