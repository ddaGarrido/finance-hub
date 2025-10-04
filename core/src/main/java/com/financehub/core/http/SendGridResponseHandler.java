package com.financehub.core.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SendGridResponseHandler extends AbstractResponseHandler<String> {

    @Override
    public String handleEntity(HttpEntity entity) throws ParseException, IOException {
        return EntityUtils.toString(entity, StandardCharsets.UTF_8);
    }

    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        return handleEntity(entity);
    }
}
