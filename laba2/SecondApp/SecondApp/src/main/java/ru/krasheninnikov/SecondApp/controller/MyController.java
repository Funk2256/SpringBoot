package ru.krasheninnikov.SecondApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.krasheninnikov.SecondApp.exception.UnsupportedCodeException;
import ru.krasheninnikov.SecondApp.exception.ValidationFailedException;
import ru.krasheninnikov.SecondApp.model.Request;
import ru.krasheninnikov.SecondApp.model.Response;
import ru.krasheninnikov.SecondApp.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MyController {

private final ValidationService validationService;

@Autowired
public MyController(ValidationService validationService) {
    this.validationService = validationService;
}
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T':HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("sucess")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            String uid = new String("123");
            if (request.getUid().equals(uid)) {
                throw new UnsupportedCodeException("Uid не может быть равен 123");
            }
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
        } catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
