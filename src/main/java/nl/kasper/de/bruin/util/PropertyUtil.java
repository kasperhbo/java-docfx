package nl.kasper.de.bruin.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The type Property util.
 */
public class PropertyUtil {
    private static final Map<Path, Properties> loadedProperties = new HashMap<>();
    private static final Map<String, String>   allProperties    = new HashMap<>();

    /**
     * HIDING THE DEFAULT CONSTRUCTOR
     */
    private PropertyUtil() {
    }

    /**
     * Get property string.
     *
     * @param key the key
     * @return the string
     * @throws PropertyUtilException the property util exception
     * @see PropertyUtilException
     * @see NullPointerException
     */
    public static String getProperty(String key) {
        if (allProperties.containsKey(key)) {
            return allProperties.get(key);
        }
        throw new PropertyUtilException("Property not found: " + key, new NullPointerException("Property not " + "found"));
    }


    /**
     * Load properties from file
     *
     * @param path Path to the properties file
     * @see PropertyUtil#loadPropertyFileIfNotLoaded(String, boolean)
     * @see Path#of(String first, String... more) Path#of(String first, String... more)
     */
    public static void loadPropertyFileIfNotLoaded(String path, boolean toEnv) {
        loadPropertyFileIfNotLoaded(Path.of(path), toEnv);
    }

    /**
     * Load properties from file
     *
     * @param path Path to the properties file
     * @throws PropertyUtilException Couldn't load properties from file
     * @see Files#notExists(Path, LinkOption...) Files#notExists(Path, LinkOption...)
     * @see Files#newBufferedReader(Path) Files#newBufferedReader(Path)
     * @see Properties#load(java.io.Reader reader) Properties#load(java.io.Reader reader)
     * @see PropertyUtilException
     */
    public static void loadPropertyFileIfNotLoaded(Path path, boolean toEnv) {
        if (loadedProperties.containsKey(path)) {
            return;
        }

        if (Files.notExists(path)) {
            throw new PropertyUtilException("File does not exist: " + path, new InvalidPathException(path.toString(), "File does not exist"));
        }

        try (var reader = Files.newBufferedReader(path)) {
            Properties properties = new Properties();
            properties.load(reader);
            loadedProperties.put(path, properties);
            allProperties.putAll(properties.entrySet().stream().collect(HashMap::new, (map, entry) -> {
                map.put(entry.getKey().toString(), entry.getValue().toString());
                if (toEnv) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                }
            }, HashMap::putAll));

        } catch (IOException e) {
            throw new PropertyUtilException("Couldn't load properties from file: " + path, e);
        }
    }

    private static final class PropertyUtilException extends RuntimeException {
        /**
         * Instantiates a new Property util exception.
         *
         * @param message the message
         * @param cause   the cause
         * @see RuntimeException#RuntimeException(String message, Throwable cause)
         */
        public PropertyUtilException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
