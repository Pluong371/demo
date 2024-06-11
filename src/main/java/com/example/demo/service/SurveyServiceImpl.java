package com.example.demo.service;

import org.springframework.boot.autoconfigure.sql.init.SqlR2dbcScriptDatabaseInitializer;
import org.springframework.stereotype.Service;

import com.example.demo.model.Survey;
import com.example.demo.untils.RestAPI;
import com.fasterxml.jackson.databind.JsonNode;

import javax.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Override
    public List<?> getSurveys(Map<String, String> filters, String sortBy, List<String> fields, boolean count)
            throws IOException {
        InputStream fis = new FileInputStream("./src/main/resources/survey_dataset.json");
        JsonReader jsonReader = Json.createReader(fis);

        JsonArray jsonArrays = jsonReader.readArray();

        jsonReader.close();
        fis.close();
        List<Survey> surveys = new ArrayList<>();
        for (JsonObject jsonObject : jsonArrays.getValuesAs(JsonObject.class)) {

            Survey servey = new Survey();
            servey.setAge(jsonObject.getString("How old are you?"));
            servey.setTimestamp(jsonObject.getString("Timestamp", null));
            servey.setIndustry(jsonObject.getString("What industry do you work in?", null));
            servey.setCurrency(jsonObject.getString("Please indicate the currency", null));
            servey.setJobTitle(jsonObject.getString("Job title", null));
            servey.setAnnualSalary(jsonObject.getString("What is your annual salary?", null));
            servey.setLocation(jsonObject.getString("Where are you located? (City/state/country)", null));
            servey.setWorkExperience(jsonObject
                    .getString("How many years of post-college professional work experience do you have?", null));
            servey.setOtherCurrency(jsonObject.getString("If \\\"Other,\\\" please indicate the currency here:", null));
            servey.setAdditionalContext(
                    jsonObject.getString("If your job title needs additional context, please clarify here:", null));
            surveys.add(servey);
        }
        // if (filters != null) {
        // filters(filters, surveys);
        // }
        if (sortBy != null) {
            sort(sortBy, surveys, null);
        }
        // if (fields.isEmpty()) {
        return getfields(fields, surveys);
        // }
        // return surveys;
    }

    @Override
    public void filters(Map<String, String> filters, List<Survey> list) {
        RestAPI.filters(filters, list);
    }

    @Override
    public List<JsonNode> getfields(List<String> fields, List<Survey> list) {
        return RestAPI.getSelectField(list, fields);
    }

    @Override
    public void sort(String sortBy, List<Survey> list, String sortDir) {
        RestAPI.sort(sortBy, list, sortDir);
        // asc desc
    }
}
