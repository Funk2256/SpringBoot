package ru.krasheninnikov.SecondApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.krasheninnikov.SecondApp.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
