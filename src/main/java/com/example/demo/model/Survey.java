package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {

    String timestamp;
    String age;
    String industry;
    String jobTitle;
    String annualSalary;
    String currency;
    String location;
    String workExperience;
    String additionalContext;
    String otherCurrency;

    // public String getTimestamp() {
    // return timestamp;
    // }

    // public void setTimestamp(String timestamp) {
    // this.timestamp = timestamp;
    // }

    // public String getAge() {
    // return age;
    // }

    // public void setAge(String age) {
    // this.age = age;
    // }

    // public String getIndustry() {
    // return industry;
    // }

    // public void setIndustry(String industry) {
    // this.industry = industry;
    // }

    // public String getJobTitle() {
    // return jobTitle;
    // }

    // public void setJobTitle(String jobTitle) {
    // this.jobTitle = jobTitle;
    // }

    // public String getAnnualSalary() {
    // return annualSalary;
    // }

    // public void setAnnualSalary(String annualSalary) {
    // this.annualSalary = annualSalary;
    // }

    // public String getCurrency() {
    // return currency;
    // }

    // public void setCurrency(String currency) {
    // this.currency = currency;
    // }

    // public String getLocation() {
    // return location;
    // }

    // public void setLocation(String location) {
    // this.location = location;
    // }

    // public String getWorkExperience() {
    // return workExperience;
    // }

    // public void setWorkExperience(String workExperience) {
    // this.workExperience = workExperience;
    // }

    // public String getAdditionalContext() {
    // return additionalContext;
    // }

    // public void setAdditionalContext(String additionalContext) {
    // this.additionalContext = additionalContext;
    // }

    // public String getOtherCurrency() {
    // return otherCurrency;
    // }

    // public void setOtherCurrency(String otherCurrency) {
    // this.otherCurrency = otherCurrency;
    // }

    // public Survey() {

    // }

    // public Survey(String count) {
    // this.timestamp = count;
    // }
}
