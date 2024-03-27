package com.zsgs.chandru.interviewpanel.model;


import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;

public class IdMaintain {

    public static void serializeId() {
       Candidate candidate = new Candidate();
        InterviewPanelDatabase.getInstance().setUpdatedId("CandidateId", candidate.getUniqueCandidateId());

    }

    public static void deserializeId() {
        Candidate candidate = new Candidate();
        int candidateUniqueId = InterviewPanelDatabase.getInstance().getUpdatedId("CandidateId");
        candidate.setUniqueCandidateId(candidateUniqueId);
    }
}