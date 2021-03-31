package com.example.SpringRest.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
