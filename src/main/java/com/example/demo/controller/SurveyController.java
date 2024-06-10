
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Survey;
import com.example.demo.service.SurveyServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SurveyController {
    @Autowired
    SurveyServiceImpl surveyService;

    @GetMapping("/survey")
    public Object getSurveys(@RequestParam(required = false) Map<String, String> filters,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) List<String> fields,
            @RequestParam(required = false, defaultValue = "false") boolean count) throws IOException {
        List<?> surveys = surveyService.getSurveys(filters, sort, fields, false);
        if (count) {
            return surveys.size();
        }
        return surveys;
    }
    public List<?> getSurveys() throws IOException {
        return surveyService.getSurveys(null, null, null, true);
    }

    
   
}
