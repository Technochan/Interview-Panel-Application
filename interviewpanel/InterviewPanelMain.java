package com.zsgs.chandru.interviewpanel;

import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.login.LoginView;
import com.zsgs.chandru.interviewpanel.model.IdMaintain;
import com.zsgs.chandru.interviewpanel.predefineddata.JobRoles;

public class InterviewPanelMain {

    private static InterviewPanelMain interviewPanelMain;

    private String appName = "Interview Panel System";

    private String appVersion = "0.1.0";

    public static InterviewPanelMain getInstance() {
        if (interviewPanelMain == null) {
            interviewPanelMain = new InterviewPanelMain();
        }
        return interviewPanelMain;
    }

    public void create() {
        // Initialize necessary components
        InterviewPanelDatabase.getInstance().deserializeIdMaintainLists();
        IdMaintain.deserializeId();
        InterviewPanelDatabase.getInstance().deserializeReceptionistDetailsMap();
        InterviewPanelDatabase.getInstance().deserializeJobRoles();

        new JobRoles();
        LoginView loginView = new LoginView();
        loginView.initiate();
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public static void main(String[] args) {
        InterviewPanelMain.getInstance().create();
    }
}
