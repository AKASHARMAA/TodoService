package com.skarvo.todosService.helpers;

import com.skarvo.todosService.models.CompletionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class CompletedTODOsHelper {

    @Value("${completeTodosService}")
    private String completeTodosService;

    @Autowired
    private RestTemplate restTemplate;

    public boolean saveStatus(final int id,
                               final boolean status) {
        final CompletionStatus completionStatus = new CompletionStatus(id, status);
        final ResponseEntity<Boolean> isSaved;

        try {
            final URI uri = new URI("http://" + completeTodosService);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            final HttpEntity request = new HttpEntity(completionStatus.toString(), headers);
            isSaved = restTemplate.postForEntity(uri, request, Boolean.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return isSaved != null ? isSaved.getBody() : false;
    }


    public boolean getStatus(final int id) {
        final ResponseEntity<Boolean> status;

        try {
            final URI uri = new URI("http://" + completeTodosService + "/" + id);
            status = restTemplate.getForEntity(uri, Boolean.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return status != null ? status.getBody() : false;
    }
}
