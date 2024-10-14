package ru.krasheninnikov.SecondApp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.krasheninnikov.SecondApp.model.Request;
import ru.krasheninnikov.SecondApp.model.Response;

@Service
@Qualifier("ModifySystemNameRequestService")

public class ModifySystemNameRequestService implements ModifyResponseService{
    @Override
    public Response modify(Response response) {
        response.setSystemTime("Service 1");
        return response;
    }

}