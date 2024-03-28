package org.epam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class ServerConfig {

    // The volatile is used as an indicator to the Java compiler to always fetch the value of a variable from
    // main memory and not cache it locally. Every read of the field will see the latest write to that field by any thread
    private volatile static ServerConfig instance;
    private JSONObject config;

    // Double Checked Lockin Singleton
    public static ServerConfig getInstance() {
        if (instance == null) {
            synchronized (ServerConfig.class) {
                if (instance == null) {
                    instance = new ServerConfig();
                }
            }
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
        String configFilePath = "config.json";
        InputStream is = getClass().getClassLoader().getResourceAsStream(configFilePath);
        if (is == null) {
            throw new IllegalArgumentException("File path not found: " + configFilePath);
        }
        JSONTokener tokener = new JSONTokener(is);
        config = new JSONObject(tokener);
    }
}
