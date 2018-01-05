package com.ac.example.cleanCode.service;

import com.ac.example.cleanCode.model.Candidate;
import com.ac.example.cleanCode.model.Course;
import com.ac.example.cleanCode.repository.CandidateRepository;
import com.ac.example.cleanCode.service.exceptions.CandidateDoesntMeetRequirementsException;
import com.ac.example.cleanCode.service.exceptions.NoSessionsApprovedException;
import com.ac.example.cleanCode.utis.WebBrowser;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alex Carvalho
 */
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public int register(Candidate candidate) {
        validateRegistration(candidate);

        return repository.save(candidate);
    }

    private void validateRegistration(Candidate candidate) {
        validateData(candidate);

        boolean isQualified = candidateExceptional(candidate) || !obviousRedFlags(candidate);

        if (!isQualified) {
            throw new CandidateDoesntMeetRequirementsException("This Candidate doesn't meet our standards.");
        }

        approveCourses(candidate);
    }

    private void validateData(Candidate candidate) {
        if (candidate.getFirstName() == null || candidate.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required.");
        }
        if (candidate.getLastName() == null || candidate.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required.");
        }
        if (candidate.getEmail() == null || candidate.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (candidate.getCourseList().isEmpty()) {
            throw new IllegalArgumentException("Can't register speaker with no sessions to present.");
        }
    }

    private boolean candidateExceptional(Candidate candidate) {
        if (candidate.getYearsExperience() > 10) return true;
        if (candidate.hasBlog()) return true;
        if (candidate.getCourseList().size() > 3) return true;

        List<String> preferredEmployers = Arrays.asList("Pluralsight", "Microsoft", "Google", "Fog Creek Software", "37Signals", "Telerik");

        return preferredEmployers.contains(candidate.getEmployer());
    }

    private boolean obviousRedFlags(Candidate candidate) {
        String emailDomain = candidate.getEmail().split("@")[1];

        List<String> ancientEmailDomains = Arrays.asList("aol.com", "hotmail.com", "prodigy.com", "compuserve.com");

        return (ancientEmailDomains.contains(emailDomain) ||
                ((candidate.getBrowser().getName() == WebBrowser.BrowserName.INTERNET_EXPLORER
                        && candidate.getBrowser().getMajorVersion() < 9)));
    }

    private void approveCourses(Candidate candidate) {
        for (Course course : candidate.getCourseList()) {
            course.setApproved(!coursesIsAboutOldTechnology(course));
        }

        boolean noSessionsApproved = candidate.getCourseList().stream().noneMatch(Course::isApproved);

        if (noSessionsApproved) {
            throw new NoSessionsApprovedException("No sessions approved");
        }
    }

    private boolean coursesIsAboutOldTechnology(Course course) {
        List<String> oldTechnologies = Arrays.asList("Cobol", "Punch Cards", "Commodore", "VBScript");

        for (String oldTechnology : oldTechnologies) {
            if (course.getTitle().contains(oldTechnology) || course.getDescription().contains(oldTechnology)) {
                return true;
            }
        }
        return false;
    }
}
