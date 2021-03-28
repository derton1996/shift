package org.example.service.validator;

import org.example.exceptions.BoundException;
import org.example.exceptions.EmptyFieldException;
import org.example.exceptions.PatternException;
import org.example.common.MonthDays;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidatorImpl implements Validator {

    private static final String PATTERN = "\\d{1,2}-\\d{1,2}-\\d{4}";

    @Override
    public void validate(String startDate, String targetDate) {
        if (startDate.trim().isEmpty() || targetDate.trim().isEmpty()) throw new EmptyFieldException();

        patternValidate(startDate);
        patternValidate(targetDate);

        boundValidate(startDate);
        boundValidate(targetDate);
    }


    void patternValidate(String date) {
        boolean match = Pattern.matches(PATTERN, date);
        if (!match) throw new PatternException(date);
    }

    void boundValidate(String inputDate) {
        String[] array = inputDate.split("-");
        int day = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]) - 1;
        int year = Integer.parseInt(array[2]);
        if (dayOutMonthBounds(day, month)) {
            boolean inLeap = dayInLeapYear(day, month, year);
            if (!inLeap) throw new BoundException(inputDate);
        }
    }

    boolean dayOutMonthBounds(int day, int month) {
        return day > MonthDays.values()[month].getDays();
    }

    boolean dayInLeapYear(int day, int month, int year) {
        return month == 1 && year % 4 == 0 && day <= 29;
    }

}
