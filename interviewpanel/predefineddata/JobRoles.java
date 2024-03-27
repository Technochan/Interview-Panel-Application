package com.zsgs.chandru.interviewpanel.predefineddata;

import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;

public class JobRoles {
    public JobRoles(){
        if(InterviewPanelDatabase.getInstance().isJobRolesEmpty()){
            InterviewPanelDatabase.getInstance().insertNewRole(1,"Software Developer");
            InterviewPanelDatabase.getInstance().insertNewRole(2,"React Developer");
            InterviewPanelDatabase.getInstance().insertNewRole(3,"IOS & Android Developer");
            InterviewPanelDatabase.getInstance().insertNewRole(4,"Debug Engineer");
            InterviewPanelDatabase.getInstance().insertNewRole(5,"Test Engineer");
            InterviewPanelDatabase.getInstance().insertNewRole(6,"Sales Engineer");
            InterviewPanelDatabase.getInstance().insertNewRole(7,"Devops Engineer");
            InterviewPanelDatabase.getInstance().insertNewRole(8,"Test Engineer");
        }
    }
}
