package org.alpercaktug.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> contextData = new HashMap<>();

    // Method to set data in the context
    public void set(String key, Object value) {
        contextData.put(key, value);
    }

    // Method to get data from the context
    public Object get(String key) {
        return contextData.get(key);
    }

    // Method to check if key exists in the context
    public boolean containsKey(String key) {
        return contextData.containsKey(key);
    }
}
