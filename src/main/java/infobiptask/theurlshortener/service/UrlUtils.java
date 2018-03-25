package infobiptask.theurlshortener.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class UrlUtils {
    public static String root = "localhost:8080/";

    private static String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static String generateRandomString(int length) throws RuntimeException {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        return sb.toString();
    }

    public static String hashMD5(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] digest = md.digest();
        return Base64.getUrlEncoder().encodeToString(digest);
    }

    public static String randomSubstring(String string, int substringLength) {
        int startPosition = RANDOM.nextInt(0, string.length() - substringLength + 1);
        return string.substring(startPosition, startPosition + substringLength);
    }

}
