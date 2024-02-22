package com.tutorialninja.qa.utils;

import java.util.Date;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 5;
    public static String generateEmailWithTimeStamp(){
        Date date = new Date();
        return "xxx"+date.toString().replace(" ","_").replace(":","_")+"@gmail.com";
    }
}
