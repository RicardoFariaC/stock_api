package com.stockapp.mainapi.models.sql.dto;

public abstract class UserBaseDTO {
    private String email;
    private String username;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
