package core;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private final Map<String, Object> values = new HashMap<>();

    public void put(String key, Object value) {
        values.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) values.get(key);
    }

    public boolean contains(String key) {
        return values.containsKey(key);
    }
}
