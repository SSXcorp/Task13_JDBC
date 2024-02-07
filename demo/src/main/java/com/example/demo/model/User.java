package com.example.demo.model;

public class User {

    private int userId;
    private String username;
    private String usersurname;
    private String email;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUsersurname() {
        return usersurname;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsersurname(String usersurname) {
        this.usersurname = usersurname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
