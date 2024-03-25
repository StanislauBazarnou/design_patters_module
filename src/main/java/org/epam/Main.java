package org.epam;

import org.epam.exception.InsufficientRightsException;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("user1", "username1", "password1", "admin");

        // Path the user tries to access
        String path = "/admin/dashboard";

        SessionManager sessionManager = new SessionManager();

        try {
            Session session = sessionManager.createSession(user1, path);
            System.out.printf("Session Created for user: %s%n", user1.getUsername());
        } catch (InsufficientRightsException e) {
            System.err.println(e.getMessage());
        }

    }
}
