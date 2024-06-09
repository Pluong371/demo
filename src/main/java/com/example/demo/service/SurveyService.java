package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Survey;

public interface SurveyService {
  List<Survey> getSurveys(Map<String, String> filters, String sortby, List<String> fields, boolean count)
      throws IOException;

  Survey getSurveybyID(String id);
}
