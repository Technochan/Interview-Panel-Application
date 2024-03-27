package com.zsgs.chandru.interviewpanel.receptionistcontrol;
import com.zsgs.chandru.interviewpanel.InterviewPanelMain;
import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.exceptionmessage.ExceptionHandling;
import com.zsgs.chandru.interviewpanel.login.LoginView;
import com.zsgs.chandru.interviewpanel.managecandidate.ManageCandidateView;
import com.zsgs.chandru.interviewpanel.manageinterview.ManageInterviewView;
import com.zsgs.chandru.interviewpanel.model.IdMaintain;
import com.zsgs.chandru.interviewpanel.predefineddata.JobRoles;

import java.util.Scanner;

public class ReceptionistControlView {

    private  ReceptionistControlView receptionistControlView;


    public ReceptionistControlView getInstamce(){
        if(receptionistControlView == null){
            receptionistControlView = new ReceptionistControlView();
        }
        return  receptionistControlView;
    }


    private ManageCandidateView manageCandidateView;
    private ManageInterviewView manageInterviewView;
    private static Scanner scan;

    public ReceptionistControlView(){
        InterviewPanelDatabase.getInstance().deserializeCandidateDetails();

        manageCandidateView = new ManageCandidateView(this);
        manageInterviewView = new ManageInterviewView();
        scan = new Scanner(System.in);
    }

    public ManageInterviewView getInstanceManageInterview(){
        return manageInterviewView;
    }
    public void initialControl() {
        System.out.println("************************************************");
        System.out.println("\t Welcome to "+ InterviewPanelMain.getInstance().getAppName()+"\t\t");
        System.out.println("\t\t\tVersion - "+ InterviewPanelMain.getInstance().getAppVersion()+"\t\t");
        System.out.println("************************************************");
        System.out.println(
                      "\n\n---------------------------------" +
                        "\n| 1 | Control Candidate Mode    |" +
                        "\n| 2 | Control Interview Mode    |" +
                        "\n| 3 | Logout                    |" +
                        "\n| 4 | Exit                      |" +
                        "\n---------------------------------"
        );
       
        int choice = ExceptionHandling.getIntInput();
        switch (choice) {
            case 1:
                manageCandidateView.initiateManage();
                break;
            case 2:
                manageInterviewView.initiateManage();
                break;
            case 3:
                System.out.println("You're logged out successfully");
                new LoginView().initiate();
                break;
            case 4:
                System.out.println("\nThanks for using " + InterviewPanelMain.getInstance().getAppName() + " - Version: " + InterviewPanelMain.getInstance().getAppVersion());
                System.exit(0);
            default:
                System.out.println("Invalid Input");
                initialControl();
        }
    }
}
