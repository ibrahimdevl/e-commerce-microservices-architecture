package com.myshop.apiGateway.exceptions;

import lombok.Data;

@Data
public class UserNotAuthentificated extends RuntimeException{

    private final String errorMessages ;

}
