package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Survey;
import com.fasterxml.jackson.databind.JsonNode;

public interface SurveyService {
  List<?> getSurveys(Map<String, String> filters, String sortby, List<String> fields, boolean count)
      throws IOException;

  void sort(String sortBy, List<Survey> list, String sortDir);

  void filters(Map<String, String> filters, List<Survey> list);

  List<JsonNode> getfields(List<String> fields, List<Survey> list);
}
