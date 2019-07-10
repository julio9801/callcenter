package com.itaca.callcenter.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {

    private static final Logger LOGGER = LogManager.getLogger(DateUtils.class);
    private static final SimpleDateFormat dfYearToSecond = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dfYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDateFormat_dd_MM_yyyy(String dateStr) {
        Date date = null;
        try {
            date = dfYearToSecond.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return date;
    }
    
     public static Date toTime(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time = null;
        try {
            time = sdf.parse(timeStr);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return time;
    }
   

    public static Date toDateFormatMM_yyyy_MM_dd(String dateStr) {

        Date date = null;
        try {
            date = dfYYYMMDD.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateFrom(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("LocalDate must not be null");
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate getLocalDateFrom(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            Instant instant = date.toInstant();
            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("America/Mexico_City"));
            return zonedDateTime.toLocalDate();
        }
    }

    public static Date getDateFrom(XMLGregorianCalendar gCalendar) {
        LocalDate localDate = LocalDate.of(gCalendar.getYear(), gCalendar.getMonth(), gCalendar.getDay());
        return getDateFrom(localDate);
    }

}
