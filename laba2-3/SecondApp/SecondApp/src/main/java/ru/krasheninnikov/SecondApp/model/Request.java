package ru.krasheninnikov.SecondApp.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {


    @NotBlank(message = "UID не может быть пустым")
    @Size(max= 32)
    private String uid;

    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max= 32)
    private String operationUid;
    private String systemName;

//    @NotBlank(message = "systemTime не может быть пустым")
    private String systemTime;

    private String source;

    @Min(1)
    @Max(100000)
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

    public String getTime(){
        return systemTime;
    }

    public String toString(){
        return "{" +
                "uid='" +uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';

    }


}