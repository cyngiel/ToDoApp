package com.ps.todoapp.security;

public record AuthTokenResponse(String jwt, long expires) {
}
