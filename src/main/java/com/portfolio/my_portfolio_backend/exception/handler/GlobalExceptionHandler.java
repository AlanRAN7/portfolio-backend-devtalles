package com.portfolio.my_portfolio_backend.exception.handler;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException (ValidationException exception, Model model){

        model.addAttribute("errors", exception.getBindingResult().getAllErrors());
        model.addAttribute("message", "Se encontraron errores de validación");
        return "error/validation";
    }
}
