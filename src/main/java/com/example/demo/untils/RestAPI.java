package com.example.demo.untils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.example.demo.model.Survey;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 * RestAPI
 */
public class RestAPI {

    public static List<JsonObject> getSelectField( List<?> list ,List<String> fields){
        return list.stream()
        .map(item -> filterFields(item, fields))
        .collect(Collectors.toList());
    }
    public static <T> void filters(Map<String, String> filters, List<T> list) {
        List<FilterField> filterFields = convertFilters(filters);
        list.removeIf(item -> {
            for (FilterField filterField : filterFields) {
                if (!checkFilterCondition(item, filterField)) {
                    return true;
                }
            }
            return false;
        });
    }
    

    private static boolean checkFilterCondition(Object item, FilterField filterField) {
        try {
            Object fieldValue = item.getClass().getDeclaredField(filterField.field).get(item);
            // Compare fieldValue with filterField.value based on the operation
            switch (filterField.opString) {
                case "eq":
                    return Objects.equals(fieldValue, filterField.value);
                case "gt":
                    return ((Comparable) fieldValue).compareTo(filterField.value) > 0;
                case "lt":
                    return ((Comparable) fieldValue).compareTo(filterField.value) < 0;
                case "gte":
                    return ((Comparable) fieldValue).compareTo(filterField.value) >= 0;
                case "lte":
                    return ((Comparable) fieldValue).compareTo(filterField.value) <= 0;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + filterField.opString);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void sort(String sortby, List<T> list, String sortDir) {
        if(sortDir == null){
            sortDir = "asc";
        }
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T item1, T item2) {
                try {
                    // Using reflection to get the field values
                    Field field1 = item1.getClass().getDeclaredField(sortby);
                    field1.setAccessible(true);
                    Comparable fieldValue1 = (Comparable) field1.get(item1);

                    Field field2 = item2.getClass().getDeclaredField(sortby);
                    field2.setAccessible(true);
                    Comparable fieldValue2 = (Comparable) field2.get(item2);

                    int comparison = fieldValue1.compareTo(fieldValue2);
                    return "asc".equalsIgnoreCase(sortDir) ? comparison : -comparison;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static JsonObject filterFields(Object item, List<String> fields) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        fields.forEach((field) ->{
            try {
                Field f = item.getClass().getDeclaredField(field);
                f.setAccessible(true);
                Object value = f.get(item);
                if (value instanceof String) {
                    builder.add(field, (String) value);
                } else if (value instanceof Number) {
                    builder.add(field, ((Number) value).doubleValue());
                } else if (value instanceof Boolean) {
                    builder.add(field, (Boolean) value);
                } else {
                    builder.add(field, value.toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return builder.build();
    }
    
    private static List<FilterField> convertFilters(Map<String, String> filters) {
        return filters.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String field;
                    String opString = "";

                    if (key.contains("[")) {
                        int startIdx = key.indexOf("[");
                        int endIdx = key.indexOf("]");
                        field = key.substring(0, startIdx);
                        opString = key.substring(startIdx + 1, endIdx);
                    } else {
                        field = key;
                        opString = "eq"; // default to equality if no operation is specified
                    }

                    return new FilterField(field, value, opString);
                })
                .collect(Collectors.toList());
    }

    private static class FilterField {
        String field;
        String value;
        String opString;

        public FilterField(String field, String value, String opString) {
            this.field = field;
            this.value = value;
            this.opString = opString;
        }

        @Override
        public String toString() {
            return "FilterField{" +
                    "field='" + field + '\'' +
                    ", value='" + value + '\'' +
                    ", opString='" + opString + '\'' +
                    '}';
        }
    }
}