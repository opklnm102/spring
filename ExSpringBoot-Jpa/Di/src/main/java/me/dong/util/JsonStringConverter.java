package me.dong.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 객체를 Json String으로 변환하는 유틸 클래스
 */
public class JsonStringConverter implements StringConverter {

    @Override
    public String toString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("convert to json string error, %s", e.toString()), e);
        }

        return result;
    }
}
