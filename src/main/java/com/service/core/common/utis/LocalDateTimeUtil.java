package com.service.core.common.utis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeUtil {
    public static LocalDateTime getNow(){
        return LocalDateTime.now();
    }

    public static LocalDateTime getYesterdayEightClock(){
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(8,0,1));
    }

    public static LocalDateTime getTodayEightClock(){
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0,0));
    }
}
