

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private static final String BASE_URL = "http://short.url/";
    private static final int SHORT_CODE_LENGTH = 6;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Map<String, String> shortToLongUrlMap = new HashMap<>();
    private Map<String, String> longToShortUrlMap = new HashMap<>();

    public String shortenUrl(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String shortCode = generateShortCode();
        String shortUrl = BASE_URL + shortCode;

        while (shortToLongUrlMap.containsKey(shortCode)) {
            shortCode = generateShortCode();
            shortUrl = BASE_URL + shortCode;
        }

        shortToLongUrlMap.put(shortCode, longUrl);
        longToShortUrlMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        String shortCode = shortUrl.substring(BASE_URL.length());
        return shortToLongUrlMap.getOrDefault(shortCode, "URL not found");
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortCode.append(CHARACTERS.charAt(index));
        }

        return shortCode.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        
        String longUrl = "https://www.exampleabcdegdsvjnxbcsjgdchvsjcjkshhskdjc27635482gds876rwrdchj63rdbfr737ghdfe645gh.com";

        String shortUrl = linkShortener.shortenUrl(longUrl);
        System.out.println("Shortened URL: " + shortUrl);

        String expandedUrl = linkShortener.expandUrl(shortUrl);
        System.out.println("Expanded URL: " + expandedUrl);
    }
}
