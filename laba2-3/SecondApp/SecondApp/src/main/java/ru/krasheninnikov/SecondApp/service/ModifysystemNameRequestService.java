package ru.krasheninnikov.SecondApp.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.krasheninnikov.SecondApp.model.Request;

@Service
public class ModifysystemNameRequestService implements ModifyRequestService{
    @Override
    public void modify(Request request) {
        request.setSystemName("Service1");
        request.setSource("from Service1");
        request.setSystemTime(request.getSystemTime());

        HttpEntity<Request>httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
