package com.zsgs.chandru.interviewpanel.managecandidate;

import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.model.Candidate;
import com.zsgs.chandru.interviewpanel.model.IdMaintain;
import com.zsgs.chandru.interviewpanel.validation.InputValidation;
import com.zsgs.chandru.librarymanagement.librarydatabase.LibraryDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ManageCandidateModel {
    private ManageCandidateView manageCandidateView;

    public ManageCandidateModel(ManageCandidateView manageCandidateView){
        this.manageCandidateView = manageCandidateView;
    }

    public void validateUserDetails(String name, String phoneNo, String emailId, String address, String education, String skill,int jobId) {
        if(!InputValidation.getInstance().isValidName(name)){
            manageCandidateView.showAlert("Invalid Name - (Must be in alphabets)");
            return;
        } else if(!InputValidation.getInstance().isValidPhoneNo(phoneNo)){
            manageCandidateView.showAlert("Invalid Phone No - (Must start with 6-9 and 10 digits)");
            return;
        } else if(InterviewPanelDatabase.getInstance().checkExistingCandidateByPhoneNo(phoneNo)){
            manageCandidateView.showAlert("User Already Found in the Database");
            return;
        } else if(!InputValidation.getInstance().isValidEmail(emailId)){
            manageCandidateView.showAlert("Invalid Email Id");
            return;
        } else if(InputValidation.getInstance().isValidAddress(address)){
            manageCandidateView.showAlert("Invalid Address");
            return;
        } else if(!InterviewPanelDatabase.getInstance().checkJobId(jobId)){
            manageCandidateView.showAlert("Invalid Job Id");
        }
        proceedToAddCandidate(name,phoneNo,emailId,address,education,skill,jobId);
    }

    public void proceedToAddCandidate(String name, String phoneNo, String emailId, String address, String education, String skill,int jobId) {
        Candidate candidate = new Candidate();
        candidate.setId();
        candidate.setName(name);
        candidate.setPhoneNo(phoneNo);
        candidate.setEmailId(emailId);
        candidate.setAddress(address);
        candidate.setEducation(education);
        candidate.setSkill(skill);
        candidate.setProcess("waiting");
        candidate.setJobId(jobId);
        candidate.setDone(false);
        candidate.setJobPosition(InterviewPanelDatabase.getInstance().getRoleNameById(jobId));
        candidate.setDateOfInterviewAttended();

        InterviewPanelDatabase.getInstance().insertCurrDayCandidateDetails(candidate.getId(), candidate);
        manageCandidateView.showText("Candidate Added Successfully \n\tCandidate Id : "+candidate.getId()+" Candidate Name : "+candidate.getName());
        InterviewPanelDatabase.getInstance().insertCandidateInterview(candidate);
        InterviewPanelDatabase.getInstance().insertCurrentDay(jobId,InterviewPanelDatabase.getInstance().getRoleNameById(jobId));
    }

    public void showCurrDayCandidateDetails() {
        HashMap<Integer,Candidate> candidateHashMap = InterviewPanelDatabase.getInstance().getCurrDayCandidateDetailsMap();
        if(candidateHashMap.isEmpty()){
            manageCandidateView.showText("Today Still No Interview Process Happen");
        } else {
            manageCandidateView.proceedToShowCandidates(candidateHashMap);
        }
    }

    public void showAllCandidate() {
        HashMap<Integer,Candidate> candidateHashMap = InterviewPanelDatabase.getInstance().getOverAllCandidateDetailsMap();
        manageCandidateView.proceedToShowCandidates(candidateHashMap);
    }

    public void updateDatabase() {
        IdMaintain.serializeId();
        InterviewPanelDatabase.getInstance().serializeIdMaintainLists();
    }
}
