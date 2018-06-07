package com.tmajor.server.integration.providers;

import com.tmajoir.lib.core.error.TMajorException;
import com.tmajor.lib.log.logger.TMajorLogger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordVerification {

    public static final TMajorLogger logger = TMajorLogger.getLogger("TJ");
    private static final String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern compiled = Pattern.compile(pattern);
    private static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    private PasswordVerification() {
    }

    public static String encrypt(String pwd) {
        return passwordEncryptor.encryptPassword(pwd);
    }

    /**
     * Check if password got at least number, uppercase, lovercase and special character
     *
     * @param pwd - input password
     * @return true if is enough
     */
    public static boolean checkComplexity(String pwd) {
        Matcher matcher = compiled.matcher(pwd);
        return matcher.matches();
    }

    public static void checkPassword(String password, String encrypted) throws TMajorException {
        if (!passwordEncryptor.checkPassword(password, encrypted)) {
            throw new TMajorException("401", logger.error("TJ", null, null, "Bad Password"));
        }
    }
}
