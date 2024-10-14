package ru.krasheninnikov.SecondApp.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SystemNameRequest {
    ERP("ERP"), WMS("WMS"), CRM("CRM");

    private final String system_name;

    SystemNameRequest(String systemName) {
        this.system_name = systemName;
    }
    @JsonValue
    public String getSystem_name() {
        return system_name;
    }
}
