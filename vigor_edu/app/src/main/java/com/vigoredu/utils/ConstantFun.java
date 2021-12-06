package com.vigoredu.utils;

import android.app.ProgressDialog;

import java.util.regex.Pattern;

public class ConstantFun {
    private static ProgressDialog progressDialog;

    // TODO check valid Mobile number...
    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!phone.isEmpty()) {
            if (!Pattern.matches("[a-zA-Z]+", phone)) {
                if (phone.length() <= 9 || phone.length() > 10) {
                    // if(phone.length() != 10) {
                    check = false;
                    // txtPhone.setError("Not Valid Number");
                } else {
                    String FirtChar = phone.substring(0, 1);
                    if (Integer.parseInt(FirtChar) >= 6) {
                        check = true;
                        //check = android.util.Patterns.PHONE.matcher(phone).matches();
                    } else {
                        check = false;
                    }
                }
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        return check;
    }

}
