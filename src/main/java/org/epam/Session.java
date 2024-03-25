package org.epam;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Session {
    private String sessionId;
    private User user;
    private String createTime;

    public Session(User user) {
        this.user = user;
        this.sessionId = UUID.randomUUID().toString();
        this.createTime = LocalDateTime.now().toString();
    }
}
