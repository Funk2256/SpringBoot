package ru.krasheninnikov.SecondApp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank
    private String uid;
    @NotBlank
    private String operationUid;
    private String systemName;
    @Pattern(regexp = "[0-9][0-9]:[0-9][0-9]")
    private String systemTime;
    private String source;
    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templatedId;
    private int productCode;
    private int smsCode;

}
