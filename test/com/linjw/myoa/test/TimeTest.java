package com.linjw.myoa.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.lf5.util.DateFormatManager;

public class TimeTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
     
       String day = "2014-07-22 09:01:57";
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       String time1 = df.format(day);
       
       System.out.println("------------------>month:"+Calendar.getInstance());
    /*   Date time ;
       time=new Date(time1);
       Calendar c = Calendar.getInstance();
       c.setTime(time);
       int month = c.get(Calendar.MONTH);
       System.out.println("------------------>month:"+month);*/
	}

}
