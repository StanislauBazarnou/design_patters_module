package org.epam;

import org.epam.exception.InsufficientRightsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionManagerTest {
    private SessionManager sessionManager;

    @BeforeEach
    public void setUp() {
        sessionManager = new SessionManager();
    }

    @Test
    void createSession_UserWithPermission_SessionCreated() throws InsufficientRightsException {
        User user = new User("user1", "username1", "password1", "admin");
        Session session = sessionManager.createSession(user, "/admin/dashboard");
        Assertions.assertNotNull(session);
        Assertions.assertEquals(user, session.getUser());
    }

    @Test
    void createSession_UserWithoutPermission_ExceptionThrown() {
        User user = new User("user2", "username2", "password2", "user");
        Assertions.assertThrows(InsufficientRightsException.class, () -> {
            sessionManager.createSession(user, "/admin/dashboard");
        });
    }
}
