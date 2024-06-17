package com.ndungutse.tectalk.model;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {

    private final String authority;

    public MyGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "MyGrantedAuthority [authority=" + authority + "]";
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
