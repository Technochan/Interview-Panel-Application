package com.zsgs.chandru.interviewpanel.manageinterview;

import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.model.Candidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ManageInterviewModel {

    private ManageInterviewView manageInterviewView;

    public ManageInterviewModel(ManageInterviewView manageInterviewView){
        this.manageInterviewView = manageInterviewView;
    }
    
    public void proceedToStartOrEnd(int choice) {
        ArrayList<Candidate> onProcessInterview = InterviewPanelDatabase.getInstance().getOnProcessInterview();
        if (onProcessInterview.isEmpty()) {
            manageInterviewView.showText("Interview Process Done");
            return;
        }

        int roleId = manageInterviewView.getJobRole();
        for (Candidate candidate : onProcessInterview) {
            if (candidate.getJobId() == roleId) {
                switch (choice) {
                    case 1:
                        if (candidate.isDone()) {
                            candidate.setProcess("completed");
                            manageInterviewView.showText("Candidate: " + candidate.getName() + " Interview Has Been Completed \nRole: " + candidate.getJobPosition());
                            InterviewPanelDatabase.getInstance().insertOverAllCandidateDetails(candidate.getId(), candidate);
                            onProcessInterview.remove(candidate);
                        } else {
                            manageInterviewView.showText("First Start The Specified Interview Role");
                        }
                        break;
                    case 2:
                        if (!candidate.isDone()) {
                            candidate.setProcess("OnGoing");
                            candidate.setDone(true);
                            manageInterviewView.showText("Candidate: " + candidate.getName() + " Was Get into the Interview \nRole: " + candidate.getJobPosition());
                        } else {
                            manageInterviewView.showText("Already Candidate: " + candidate.getName() + " Interview is going on");
                        }
                        break;
                }
                return;
            }
        }
    }

    public void proceedStatusAboutInterview() {
        manageInterviewView.showStatusAboutInterview(InterviewPanelDatabase.getInstance().getOnProcessInterview());
    }

    public void proceedToShowCurrJobRole() {
        manageInterviewView.showAllJobRoles(InterviewPanelDatabase.getInstance().getcurrentDay());
    }

    public void proceedToAddNewRole(int roleId, String role) {
        if(!InterviewPanelDatabase.getInstance().checkJobId(roleId)){
            InterviewPanelDatabase.getInstance().insertNewRole(roleId,role);
            manageInterviewView.showText("New Job Role Added Successfully");
        } else {
            manageInterviewView.showText("Invalid Id : Id Already Matched With Other Role");
        }
    }

    public void proceedToShowJobRole() {
        HashMap<Integer,String > jobRoles = InterviewPanelDatabase.getInstance().getJobRoles();
        manageInterviewView.showAllJobRoles(jobRoles);
    }

    public void proceedCandidateNotAttendInterview(int candidateId) {
        ArrayList<Candidate> onProcessInterview = InterviewPanelDatabase.getInstance().getOnProcessInterview();
        Candidate candidateToRemove = null;
        for (Candidate candidate : onProcessInterview) {
            if (candidate.getId() == candidateId) {
                candidateToRemove = candidate;
                break;
            }
        }

        if (candidateToRemove != null) {
            candidateToRemove.setProcess("Interview Not Attended");
            onProcessInterview.remove(candidateToRemove);
            InterviewPanelDatabase.getInstance().insertOverAllCandidateDetails(candidateToRemove.getId(),candidateToRemove);
            manageInterviewView.showText("Candidate Removed Successfully");
        } else {
            manageInterviewView.showText("Candidate Not Found in this Interview Process List");
        }


    }

    public void updateDatabase() {
        InterviewPanelDatabase.getInstance().serializeJobRoles();
        InterviewPanelDatabase.getInstance().serializeReceptionistDetail();
        InterviewPanelDatabase.getInstance().serializeOverAllCandidateDetails();
    }
}
