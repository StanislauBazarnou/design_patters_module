package org.epam;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String username;
    private String password;
    private String accessLevel;

    public User (String id, String username, String password, String accessLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }
}
