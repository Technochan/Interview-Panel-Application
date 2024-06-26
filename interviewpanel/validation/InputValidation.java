package com.zsgs.chandru.interviewpanel.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    private static InputValidation inputValidation;

    public static InputValidation getInstance(){
        if(inputValidation == null){
            inputValidation = new InputValidation();
        }
        return inputValidation;
    }
    private static final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String indianPhoneNumberRegex = "^[6-9]\\d{9}$";
    private static final String usernameRegex = "^[a-zA-Z]+$";

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValidPhoneNo(String phoneNo){
        Pattern pattern = Pattern.compile(indianPhoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }
    public boolean isValidName(String name){
        if(name.length() < 3 || name.length() > 30 ) return false;
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isValidAddress(String address){
        return address.length() < 3 || address.length() > 50;
    }
    public boolean isValidPassword(String pasword){
        return pasword.length() < 5 || pasword.length() > 30;
    }

}
