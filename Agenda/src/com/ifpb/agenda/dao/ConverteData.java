package com.ifpb.agenda.dao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Essa classe contém métodos referentes a conversão de datas
 * @author ricarte
 */
public class ConverteData {

    /**
     * Método de conversão (Date - LocalDate)
     * @param d A data (no formato Date) que será convertida
     * @return A data no formato LocalDate
     */
    public static LocalDate toLocalDate(Date d) {
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
    
}
