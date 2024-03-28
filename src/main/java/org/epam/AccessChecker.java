package org.epam;

/*
 Singleton class and its purpose is to check if the User has the
 necessary permissions to access a given path
 */

public class AccessChecker {
    private static AccessChecker instance;
    private ServerConfig config = ServerConfig.getInstance();

    public static synchronized AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    private AccessChecker() {
    }

    public boolean mayAccess(User user, String path) {
        String userLevel = config.getAccessLevel(user);
        if (path.startsWith("/admin") && !userLevel.equals("admin")) {
            return false;
        }
        return true;
    }

}
