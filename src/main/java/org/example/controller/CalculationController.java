package org.example.controller;

import org.example.entity.Period;
import org.example.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;

@Controller
public class CalculationController {

    CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public String calculate(@ModelAttribute("period") Period period,
                            Model model) throws ParseException {
        model.addAttribute("result", calculationService.calcShiftDate(period));
        return "calculate";
    }

}
