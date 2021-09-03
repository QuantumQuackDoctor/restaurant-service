package com.smoothstack.user.errors;

public class RestaurantNotFoundException extends  Exception{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
