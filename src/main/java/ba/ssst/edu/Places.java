package ba.ssst.edu;

import java.util.HashMap;
import java.util.Map;

public class Places {
    private final char shortcode;
    private final String location;
    private static Map<Character, String> placesMap = new HashMap<>();

    public Places(char shortcode, String location) {
        this.shortcode = shortcode;
        this.location = location;
    }
    public static void add(char shortcode, String location) {
        placesMap.put(shortcode, location);
    }
    public static Map<Character, String> getPlacesMap() {
        return placesMap;
    }
    public static boolean exists(char shortcode) {
        return placesMap.containsKey(shortcode);
    }
}
