package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Survey;

import javax.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SurveyServiceImpl implements SurveyService {
    // private List<Survey> surveys;
    // public void innit(){
    // try{
    // File fileSurvey = ResourceUtils.getFile("survey_dataset.json");
    // ObjectMapper mapper = new ObjectMapper();
    // surveys = mapper.readValue(fileSurvey,
    // mapper.getTypeFactory().constructCollectionType(List.class, Survey.class));
    // } catch (IOException e) {
    // e.printStackTrace();
    // surveys= new ArrayList<>();
    //
    // }
    // }
    @Override
    public List<Survey> getSurveys(Map<String, String> filters, String sortBy, List<String> fields, boolean count)
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
            servey.setWorkExperience(jsonObject.getString("How many years of post-college professional work experience do you have?", null));
            servey.setOtherCurrency(jsonObject.getString("If \\\"Other,\\\" please indicate the currency here:", null));
            servey.setAdditionalContext(jsonObject.getString("If your job title needs additional context, please clarify here:",null));
            surveys.add(servey);
        }
        return surveys;
    }

    // public Survey getSurveyById(int id) {
    // return surveys.stream()
    // .filter(data -> true)
    // .findFirst()
    // .orElse(null);
    // }
    // private boolean matchesFilters(Survey survey, Map<String, String> filters){
    // return true;
    // }
    // private Survey createSurvey(Survey data, List<String> fields){
    // Survey survey = new Survey();
    // return data;
    // }

    @Override
    public Survey getSurveybyID(String id) {
        return null;
    }
}
