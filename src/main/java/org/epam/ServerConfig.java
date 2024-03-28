package org.epam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class ServerConfig {
    private static ServerConfig instance;
    private JSONObject config;

    private static String configFilePath = "config.json";

    public static synchronized ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    private ServerConfig() {
        loadConfigFromFile();
    }

    public String getAccessLevel(User u) {
        JSONArray users = config.getJSONArray("users");
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getString("id").equals(u.getId())) {
                return user.getString("accessLevel");
            }
        }
        return "user";
    }

    private void loadConfigFromFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(configFilePath);
        if (is == null) {
            throw new IllegalArgumentException("File path not found: " + configFilePath);
        }
        JSONTokener tokener = new JSONTokener(is);
        config = new JSONObject(tokener);
    }
}
