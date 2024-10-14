package ru.krasheninnikov.SecondApp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.krasheninnikov.SecondApp.exception.ValidationFailedException;
import ru.krasheninnikov.SecondApp.model.*;
import ru.krasheninnikov.SecondApp.service.ModifyResponseService;
import ru.krasheninnikov.SecondApp.service.ValidationService;
import ru.krasheninnikov.SecondApp.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

private final ValidationService validationService;
private final ModifyResponseService modifyResponseService;

@Autowired
public MyController(ValidationService validationService,
                    @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
    this.validationService = validationService;
    this.modifyResponseService = modifyResponseService;
}
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("request: {}", request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("request: {}", request);

        try {
            validationService.isValid(bindingResult);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("binding-result-error: {}", bindingResult.getFieldError().toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("request: {}", request);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("request: {}", request);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);

    }
}
