package ru.krasheninnikov.SecondApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String uid;
    private String operationUid;
    private String systemTime;
//    private SystemNameRequest systemNameRequest;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;

}
