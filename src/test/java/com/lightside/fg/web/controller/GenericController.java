package com.lightside.fg.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Anwar
 */
public interface GenericController {

    MediaType mediaTypeAppilcationJson = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    void setConverters(HttpMessageConverter<?>[] converters);

    String json(Object o) throws IOException;


}
