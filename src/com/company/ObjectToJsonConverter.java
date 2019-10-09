package com.company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {
    public String toJson(Employee employee){
        ObjectMapper jsonMapper = new ObjectMapper();

        String jsonString = null;
        try {
            jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  jsonString;
    }
}
