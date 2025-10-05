package com.financehub.core.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

@AllArgsConstructor
@Getter @Setter
public class Response {
    private int statusCode;
    private String body;
    private Map<String, String> headers;

    public Response() {
        this.reset();
    }

    public void reset() {
        this.statusCode = 0;
        this.body = "";
        this.headers = null;
    }

    public Document html() {
        return Jsoup.parse(this.body);
    }
}
