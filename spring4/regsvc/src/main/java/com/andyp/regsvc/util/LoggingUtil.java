package com.andyp.regsvc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: Andy Paladino
 * @version: 10/7/14
 */
public class LoggingUtil {

    private static ObjectMapper objectMapper;

    public LoggingUtil(){
        init();
    }

    private static void init(){
        objectMapper = new ObjectMapper();
        objectMapper.getDeserializationConfig()
                .withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        DeserializationFeature.EAGER_DESERIALIZER_FETCH);
    }

    public static String toJSON(Object object){

        if(objectMapper == null){
            init();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }
}
