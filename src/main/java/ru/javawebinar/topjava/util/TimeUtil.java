package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        if (lt == null || startTime == null || endTime == null) return true;
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
