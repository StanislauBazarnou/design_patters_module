package org.epam.exception;

import org.epam.User;

public class InsufficientRightsException extends Exception {

    private User user;
    private String accessedPath;

    public InsufficientRightsException(User user, String accessedPath) {
        super(String.format("User %s has insufficient rights to access %s", user.getUsername(), accessedPath));
        this.user = user;
        this.accessedPath = accessedPath;
    }

    public User getUser() {
        return this.user;
    }

    public String getAccessedPath() {
        return this.accessedPath;
    }
}
