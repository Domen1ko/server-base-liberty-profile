package com.tmajor.server.integration.providers;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordVerificationTest {

    @Test
    public void encrypt() {

        String passs = "Gianni1234!";
        Assert.assertFalse(passs.equalsIgnoreCase(PasswordVerification.encrypt(passs)));
    }

    @Test
    public void checkComplexity() {

        Assert.assertFalse(PasswordVerification.checkComplexity("abc"));
        Assert.assertFalse(PasswordVerification.checkComplexity("abcdefg"));
        Assert.assertTrue(PasswordVerification.checkComplexity("Gianni1234!"));
    }
}