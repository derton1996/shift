package org.example.service;

import org.apache.commons.lang3.StringUtils;
import org.example.entity.Period;
import org.example.exceptions.BoundException;
import org.example.exceptions.EmptyFieldException;
import org.example.exceptions.PatternException;
import org.example.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.example.common.DayType.*;

@Service
public class CalculationServiceImpl implements CalculationService {

    private static long DAY_MILLIS = 24 * 60 * 60 * 1000;

    Validator validator;

    @Autowired
    public CalculationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public String calcShiftDate(Period period) throws ParseException {
        String startDate = period.getStartDate();
        String targetDate = period.getTargetDate();
        if (startDate == null || targetDate == null) return StringUtils.EMPTY;

        try {
            validator.validate(startDate, targetDate);
        } catch (PatternException | BoundException | EmptyFieldException e ) {
            return e.getMessage();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date start = formatter.parse(startDate);
        Date target = formatter.parse(targetDate);

        long startDays = start.getTime() / DAY_MILLIS;
        long targetDays = target.getTime() / DAY_MILLIS;

        return dayType(startDays, targetDays);
    }

    private String dayType(long startDays, long targetDays) {
        long t = (targetDays - startDays) % 3;
        return (t == 1 || t == 2) ? DAY_OFF : WORKING_DAY;
    }

}
