package org.example.service;

import org.example.entity.Period;

import java.text.ParseException;

public interface CalculationService {

    String calcShiftDate(Period period) throws ParseException;

}
