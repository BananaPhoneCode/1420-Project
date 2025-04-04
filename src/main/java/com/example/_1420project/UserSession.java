package com.example._1420project;

//an entire class dedicated to tracking the current logged in user's username and role
//the backbone of the management of role-based views

public class UserSession {
    private static UserSession instance;
    private String userId;     // studentId or facultyId
    private String role;       // "student", "faculty", or "admin"

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public void clear() {
        userId = null;
        role = null;
    }
}
