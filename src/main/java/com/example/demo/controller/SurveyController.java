
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Survey;
import com.example.demo.service.SurveyServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@RestController
public class SurveyController {
    @Autowired
    SurveyServiceImpl surveyService;

    @GetMapping("/survey")
    // public void getSurveys(@RequestParam(required = false) Map<String, String>
    // filters,
    // @RequestParam(required = false) String sort,
    // @RequestParam(required = false) List<String> fields,
    // @RequestParam(required = false, defaultValue = "false") boolean count) {
    //
    // }
    public List<?> getSurveys() throws IOException {
        return surveyService.getSurveys(null, null, null, true);
    }

    @GetMapping("/survey/{id}")
    public Survey getSurveyDataById(@PathVariable String id) {
        return surveyService.getSurveybyID(id);
    }

}
