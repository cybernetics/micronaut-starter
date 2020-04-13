package io.micronaut.starter.template;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class PropertiesTemplate implements Template {

    private final String path;
    private final Properties properties;

    public PropertiesTemplate(String path, Map<String, Object> config) {
        this.path = path;
        this.properties = transform(new LinkedProperties(), "", config);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        properties.store(outputStream, null);
    }

    private Properties transform(Properties finalConfig, String prefix, Map<String, Object> config) {
        for (Map.Entry<String, Object> entry : config.entrySet()) {
            transform(finalConfig, prefix + entry.getKey(), entry.getValue());
        }
        return finalConfig;
    }

    private void transform(Properties finalConfig, String prefix, Object value) {
        if (value instanceof Map) {
            transform(finalConfig, prefix + ".", (Map<String, Object>) value);
        } else if (value instanceof List) {
            List list = (List) value;
            for (int i = 0; i < list.size(); i++) {
                transform(finalConfig, prefix + "[" + i + "]", list.get(i));
            }
        } else {
            finalConfig.put(prefix, value.toString());
        }
    }

    public class LinkedProperties extends Properties {
        private final HashSet<Object> keys = new LinkedHashSet<>();

        public LinkedProperties() {
        }

        public Iterable<Object> orderedKeys() {
            return Collections.list(keys());
        }

        public Enumeration<Object> keys() {
            return Collections.enumeration(keys);
        }

        public Object put(Object key, Object value) {
            keys.add(key);
            return super.put(key, value);
        }
    }
}
