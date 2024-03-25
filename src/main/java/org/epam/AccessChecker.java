package org.epam;

/*
 Singleton class and its purpose is to check if the User has the
 necessary permissions to access a given path
 */

public class AccessChecker {
    private static AccessChecker instance;
    private ServerConfig config = ServerConfig.getInstance();

    public synchronized static AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    private AccessChecker() {
        /*
        According to the given task, you might need to establish a link to a configuration
        However, in your provided snippets, the configuration is initialized directly in the class body:
            private ServerConfig config = ServerConfig.getInstance();
        Therefore, if you don't require any other initialization tasks, you can leave the
        constructor empty:
        private AccessChecker() {
            // Constructor intentionally left empty
        }
        This way, when the getInstance() method is called for the first time and creates a
        new AccessChecker instance, no extra initialization will be performed
        If you later determine that some startup logic is needed when an AccessChecker instance
        is created - for example, logging, loading settings from the ServerConfig, setting up
        default values, or other actions - this would go in the private constructor
         */
    }

    public boolean mayAccess(User user, String path) {
        String userLevel = config.getAccessLevel(user);
        if (path.startsWith("/admin") && !userLevel.equals("admin")) {
            return false;
        }
        return true;
    }

}
