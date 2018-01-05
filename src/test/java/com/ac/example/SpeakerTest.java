package com.ac.example;

import com.ac.example.cleanCode.model.Candidate;
import com.ac.example.cleanCode.model.Course;
import com.ac.example.cleanCode.repository.CandidateRepository;
import com.ac.example.cleanCode.repository.CandidateRepositoryImpl;
import com.ac.example.cleanCode.service.CandidateService;
import com.ac.example.cleanCode.service.CandidateServiceImpl;
import com.ac.example.cleanCode.service.exceptions.CandidateDoesntMeetRequirementsException;
import com.ac.example.cleanCode.service.exceptions.NoSessionsApprovedException;
import com.ac.example.cleanCode.utis.WebBrowser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Alex Carvalho
 */
public class SpeakerTest {

    private CandidateRepository repository;
    private CandidateService service;

    @Before
    public void before() {
        repository = new CandidateRepositoryImpl();
        service = new CandidateServiceImpl(repository);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterEmptyFirstNameThrowsArgumentNullException() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setFirstName("");

        service.register(speaker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterEmptyLastNameThrowsIllegalArgumentException() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setLastName("");

        service.register(speaker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterEmptyEmailIllegalArgumentException() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setEmail("");

        service.register(speaker);
    }

    @Test
    public void testRegisterWorksForPrestigiousEmployerButHasRedFlagsReturnsSpeakerId() {
        Candidate speaker = GetSpeakerWithRedFlags();
        speaker.setEmployer("Microsoft");

        Integer speakerId = service.register(speaker);

        Assert.assertNotNull(speakerId);
    }

    @Test
    public void testRegisterHasBlogButHasRedFlagsReturnsSpeakerId() {
        Candidate speaker = GetSpeakerWithRedFlags();

        Integer speakerId = service.register(speaker);

        Assert.assertNotNull(speakerId);
    }

    @Test
    public void testRegisterHasCertificationsButHasRedFlagsReturnsSpeakerId() {
        Candidate speaker = GetSpeakerWithRedFlags();
        speaker.setCertifications(Arrays.asList("cert1", "cert2", "cert3", "cert4"));

        Integer speakerId = service.register(speaker);

        Assert.assertNotNull(speakerId);
    }

    @Test(expected = NoSessionsApprovedException.class)
    public void testRegisterSingleSessionThatsOnOldTechThrowsNoSessionsApprovedException() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setCourseList(Arrays.asList(new Course("Cobol for dummies", "Intro to Cobol")));

        service.register(speaker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNoSessionsPassedThrowsArgumentException() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setCourseList(Collections.emptyList());

        service.register(speaker);
    }

    @Test(expected = CandidateDoesntMeetRequirementsException.class)
    public void testRegisterDoesntAppearExceptionalAndUsingOldBrowserThrowsSpeakerDoesntMeetRequirementsException() {
        Candidate speakerThatDoesntAppearExceptional = GetSpeakerThatWouldBeApproved();
        speakerThatDoesntAppearExceptional.setHasBlog(false);
        speakerThatDoesntAppearExceptional.setBrowser(new WebBrowser(WebBrowser.BrowserName.INTERNET_EXPLORER, 6));

        service.register(speakerThatDoesntAppearExceptional);
    }

    @Test(expected = CandidateDoesntMeetRequirementsException.class)
    public void testRegisterDoesntAppearExceptionalAndHasAncientEmailThrowsSpeakerDoesntMeetRequirementsException() {
        Candidate speakerThatDoesntAppearExceptional = GetSpeakerThatWouldBeApproved();
        speakerThatDoesntAppearExceptional.setHasBlog(false);
        speakerThatDoesntAppearExceptional.setEmail("name@aol.com");

        service.register(speakerThatDoesntAppearExceptional);
    }

    private Candidate GetSpeakerThatWouldBeApproved() {
        return new Candidate(
                "First",
                "Last",
                "example@domain.com",
                1,
                true,
                "",
                new WebBrowser(WebBrowser.BrowserName.UNKNOWN, 1),
                Collections.emptyList(),
                "Example Employer",
                Arrays.asList(new Course("test title", "test description")));
    }


    private Candidate GetSpeakerWithRedFlags() {
        Candidate speaker = GetSpeakerThatWouldBeApproved();
        speaker.setEmail("tom@aol.com");
        speaker.setBrowser(new WebBrowser(WebBrowser.BrowserName.INTERNET_EXPLORER, 6));
        return speaker;
    }
}