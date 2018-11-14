package edu.david.demo.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import edu.david.demo.model.Attribution;
import edu.david.demo.model.Image;
import edu.david.demo.model.Response;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class JaxbTest {
    public static void main(String[] args) throws IOException {
        Response response = new Response();
        response.getImages().add(new Image());
        response.getAttributions().add(new Attribution());
        ObjectMapper objectMapper = new ObjectMapper()
            .disable(WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_NULL_MAP_VALUES)
            .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .enable(SerializationFeature.WRAP_ROOT_VALUE)
            .enable(DeserializationFeature.UNWRAP_ROOT_VALUE)
            .enable(USE_WRAPPER_NAME_AS_PROPERTY_NAME)
            .registerModule(new JaxbAnnotationModule())
            .setSerializationInclusion(NON_NULL);
        String json = objectMapper.writeValueAsString(response);
        System.out.println(json);
        Response value = objectMapper.readValue(json, Response.class);
        System.out.println(objectMapper.writeValueAsString(value));
    }
}
