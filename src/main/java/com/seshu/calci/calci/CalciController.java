package com.seshu.calci.calci;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalciController {

    @GetMapping("/")
    public String showCalculator(Model model) {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam String num1,
            @RequestParam String num2,
            @RequestParam String operator,
            Model model) {

        try {
            double number1 = Double.parseDouble(num1);
            double number2 = Double.parseDouble(num2);
            double result = 0;
            switch (operator) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        model.addAttribute("error", "Cannot divide by zero");
                        return "calculator";
                    }
                    break;
            }
            model.addAttribute("result", result);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input");
        }
        return "calculator";
    }
}
//
//package com.seshu.calci.calci;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class CalciController {
//
//    @GetMapping("/")
//    public String showCalculator(Model model) {
//        return "calculator";  // Return the calculator view
//    }
//
//    @PostMapping("/calculate")
//    public String calculate(@RequestParam String num1,
//                            @RequestParam String num2,
//                            @RequestParam String operator,
//                            Model model) {
//        try {
//            double number1 = Double.parseDouble(num1);
//            double number2 = Double.parseDouble(num2);
//            double result = 0;
//            switch (operator) {
//                case "+":
//                    result = number1 + number2;
//                    break;
//                case "-":
//                    result = number1 - number2;
//                    break;
//                case "*":
//                    result = number1 * number2;
//                    break;
//                case "/":
//                    if (number2 != 0) {
//                        result = number1 / number2;
//                    } else {
//                        model.addAttribute("error", "Cannot divide by zero");
//                        return "calculator";
//                    }
//                    break;
//            }
//            model.addAttribute("result", result);
//        } catch (NumberFormatException e) {
//            model.addAttribute("error", "Invalid input");
//        }
//        return "calculator";  // Return the updated view with result
//    }
//}
//
