package com.tutorialninja.qa.utils;

import java.util.Date;

public class Utilities {
    public static String generateEmailWithTimeStamp(){
        Date date = new Date();
        return "xxx"+date.toString().replace(" ","_").replace(":","_")+"@gmail.com";
    }
}
