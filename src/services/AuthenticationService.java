package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class AuthenticationService {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("bruh, SHA-256 algorithm not found", e);
        }
    }

    public static boolean validatePasswordStrength(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
        String specialChars = "><,\"';:\\/|[]{}+=()*&^%$#!";

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            else if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            else if (specialChars.indexOf(c) >= 0) {
                hasSpecial = true;
            }
        }

        return hasLower && hasUpper && hasDigit && hasSpecial;
    }

    public static boolean validateEmailFormat(String email) {
        long atCount = email.chars().filter(ch -> ch == '@').count();
        if (atCount != 1) {
            return false;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String prefix = parts[0];
        String domain = parts[1];

        String prefixRegex = "^[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]$";
        if (!Pattern.matches(prefixRegex, prefix) || prefix.contains("..")) {
            return false;
        }

        String domainRegex = "^[a-zA-Z0-9][a-zA-Z0-9.-]*\\.[a-zA-Z]{2,}$";
        return Pattern.matches(domainRegex, domain) && !domain.contains("..");
    }
}
