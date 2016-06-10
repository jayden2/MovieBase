package com.jayden.moviebase;

/**
 * Created by Jayden on 11-Jun-16.
 */
public class User {
    private String email, username, token;
    private Long user_id;

    public User() {
    }

    //getters
    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public long getUserId() { return user_id;  }

    public String getToken() { return token; }

    //setters
    public void setEmail(String email) { this.email = email; }

    public void setUsername(String username) { this.username = username; }

    public void setUserId(long user_id) { this.user_id = user_id;  }

    public void setToken(String token) { this.token = token; }
}
