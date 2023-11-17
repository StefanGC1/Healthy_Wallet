package com.example.healthy_wallet;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private LocalDate startDate; // Unused for now
    private LocalDate endDate; // Unused for now

    public CalendarManager() {
        // Initialize with default or current values
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    public List<LocalDate> getDatesOfMonth(YearMonth yearMonth) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        return dates;
    }

    public List<LocalDate> getDatesOfYear(int year) {
        List<LocalDate> dates = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month);
            dates.addAll(getDatesOfMonth(yearMonth));
        }
        return dates;
    }
}