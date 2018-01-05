package com.ac.example.cleanCode.model;

import com.ac.example.cleanCode.utis.WebBrowser;

import java.util.List;

/**
 * @author Alex Carvalho
 */
public class Candidate {

    private String firstName;
    private String lastName;
    private String email;
    private int yearsExperience;
    private boolean hasBlog;
    private String blogURL;
    private WebBrowser browser;
    private List<String> certifications;
    private String employer;
    private List<Course> courseList;
    private int registrationFee;

    public Candidate(String firstName, String lastName, String email, int yearsExperience, boolean hasBlog, String blogURL, WebBrowser browser, List<String> certifications, String employer, List<Course> courseList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearsExperience = yearsExperience;
        this.hasBlog = hasBlog;
        this.blogURL = blogURL;
        this.browser = browser;
        this.certifications = certifications;
        this.employer = employer;
        this.courseList = courseList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WebBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(WebBrowser browser) {
        this.browser = browser;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public boolean hasBlog() {
        return hasBlog;
    }

    public void setHasBlog(boolean hasBlog) {
        this.hasBlog = hasBlog;
    }

    public String getBlogURL() {
        return blogURL;
    }

    public void setBlogURL(String blogURL) {
        this.blogURL = blogURL;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }
}
