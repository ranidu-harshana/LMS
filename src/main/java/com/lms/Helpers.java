package com.lms;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helpers {
    static HttpSession session;
    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Set Session Message
    public static String checkRole(HttpSession session) {
        if (session.getAttribute("role") == null) {
            return "redirect:/";
        } else {
            if (!session.getAttribute("role").equals("admin")) {
                return "redirect:/";
            }
        }
        return null;
    }

    public static boolean checkAdmin(HttpSession session) {
        if (session.getAttribute("role") != null) {
            return session.getAttribute("role").equals("admin");
        }
        return false;
    }

    public static void setSessionMessage(HttpSession session, Boolean result, String successmessage, String errormessage) {
        String key = "message";
        Helpers.session = session;
        setMessage(result, successmessage, errormessage, key);
    }

    public static void setSessionMessage(HttpSession session, Boolean result, String key, String successmessage, String errormessage) {
        Helpers.session = session;
        setMessage(result, successmessage, errormessage, key);
    }

    private static void setMessage(Boolean result, String successmessage, String errormessage, String key) {
        String message, role;
        if (result) {
            message = successmessage;
            role = "success";
        } else {
            message = errormessage;
            role = "danger";
        }
        Helpers.session.setAttribute(key, message);
        Helpers.session.setAttribute("alert_role", role);
    }
}
