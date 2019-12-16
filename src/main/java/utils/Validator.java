package utils;

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class Validator {
    public String validateInput(String username, String password, String phone, String email, String dateOfBirth) {
        if(!validateUsername(username)) {
            System.out.println("username is invalid !!!");
            return "username is invalid !!!";
        }

        if(!validatePassword(password)) {
            System.out.println("password is invalid !!!");
            return "password is invalid !!!";
        }

        if(!validatePhone(phone)) {
            System.out.println("phone is invalid !!!");
            return "phone is invalid !!!";
        }

        if(!validateEmail(email)) {
            System.out.println("email is invalid !!!");
            return "email is invalid !!!";
        }

        if(!validateDateOfBirth(dateOfBirth)) {
            System.out.println("date of birth invalid !!!");
            return "date of birth invalid !!!";
        }
        return "valid";
    }
    public boolean validateUsername(String username) {
        /**
         * username cannot null, empty or already exist in DB
         */
//        boolean check = false;
        if(username == null || username.isEmpty() || username.trim().isEmpty())
            return false;
        return true;
    }

    public boolean validatePassword(String password) {
        /**
         *password cannot null, empty
         */
        if(password == null || password.isEmpty() || password.trim().isEmpty())
            return false;
        return true;
    }

    public boolean validatePhone(String phone) {
        /**
         *phone number must be 10 or 11 number
         */
        return phone.matches("[0-9]+") && (phone.length() == 10 || phone.length() == 11);
    }

    public boolean validateEmail(String email) {
        /**
         *validate email
         */
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean validateDateOfBirth(String dateOfBirth) {
        /**
         *validate Date of birth
         */
        Pattern pattern = Pattern.compile("\\d{1,2}[/]\\d{1,2}[/]\\d{4}");
        return pattern.matcher(dateOfBirth).matches();
    }

    public String Encrypt(String password, String typeEncoder) {
        try {
            MessageDigest md = MessageDigest.getInstance(typeEncoder);
            md.update(password.getBytes());
            byte[] byteData = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "d8f9f3c7e0933b1c51b161f6ae4244c9";
        }
    }
}
