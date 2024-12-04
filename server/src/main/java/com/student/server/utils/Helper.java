package com.student.server.utils;

import com.student.server.config.exception.RestApiException;
import com.student.server.data.response.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ResponseEntity<?> createResponseEntity(ResponseObject<?> responseObject) {
        return new ResponseEntity<>(responseObject, responseObject.getStatus());
    }

    public static String longToDateString(Long birthDate) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(birthDate), ZoneId.systemDefault());
        return dateTime.format(formatter);
    }

    public static Long stringToDateLong(String birthDate) {
        try {
            LocalDate localDate = LocalDate.parse(birthDate, formatter);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new RestApiException("Date format error");
        }
    }

    public static LocalDateTime stringToLocalDateTime(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateTimeStr = dateStr + " 00:00";
        return LocalDateTime.parse(dateTimeStr, format);
    }
}
