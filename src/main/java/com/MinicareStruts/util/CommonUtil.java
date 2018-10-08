package com.MinicareStruts.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {
    private static final Logger logger = Logger.getLogger(CommonUtil.class.getName());
    public static String getHashedPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = new String(hash);
        }
        catch(NoSuchAlgorithmException n) {
            logger.log(Level.SEVERE, "Exception While Hashing Password" + n);
        }
        return hashedPassword;
    }

    public static boolean isSeeker(String type) {
        if(StringUtils.equalsIgnoreCase(type,Constants.SEEKER))
            return true;
        return false;
    }

    public static boolean isSitter(String type) {
        if(StringUtils.equalsIgnoreCase(type,Constants.SITTER))
            return true;
        return false;
    }
}