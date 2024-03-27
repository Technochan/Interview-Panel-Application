package com.zsgs.chandru.interviewpanel.managecandidate;
import com.zsgs.chandru.interviewpanel.exceptionmessage.ExceptionHandling;
import com.zsgs.chandru.interviewpanel.model.Candidate;
import com.zsgs.chandru.interviewpanel.receptionistcontrol.ReceptionistControlView;
import com.zsgs.chandru.librarymanagement.colortext.Color;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ManageCandidateView {
    private ManageCandidateModel manageCandidateModel;
    private ReceptionistControlView receptionistControlView;
    private static Scanner scan;

    public ManageCandidateView(ReceptionistControlView receptionistControlView){
        this.receptionistControlView = receptionistControlView;
        manageCandidateModel = new ManageCandidateModel(this);
        scan = new Scanner(System.in);
    }

    public void initiateManage() {
        whileLoop:while(true)
        {
            System.out.println(
                           "\n\n------------------------------------------" +
                            "\n| 1 | Add New Candidate Details           |" +
                            "\n| 2 | Show Current Day Candidate Details  |" +
                            "\n| 3 | Show OverAll Candidates In Database |" +
                            "\n| 4 | Go Back                             |" +
                            "\n-------------------------------------------"
            );
            int choice = ExceptionHandling.getIntInput();
            switch(choice)
            {
                case 1 : addNewCandidate();
                    break;
                case 2 : manageCandidateModel.showCurrDayCandidateDetails();
                        break;
                case 3 : manageCandidateModel.showAllCandidate();
                    break;
                case 4 : manageCandidateModel.updateDatabase();
                    break whileLoop;
                default: System.out.println("Invalid Choice Try Again");
            }
        }
        new ReceptionistControlView().initialControl();
    }

    public void addNewCandidate() {
            System.out.println("\n\nEnter the Candidate Name:");
            String name = scan.next();
            System.out.println("Enter the Candidate PhoneNo:");
            String phoneNo = scan.next();
            System.out.println("Enter the Candidate EmailId:");
            String emailId = scan.next();
            System.out.println("Enter the Candidate Address:");
            String address = scan.next();
            System.out.println("Enter the Candidate Education:");
            String education = scan.next();
            System.out.println("Enter the Candidate Skills");
            String skill = scan.next();
            System.out.println("Choose The Job Role");
            receptionistControlView.getInstamce().getInstanceManageInterview().getInstanceManageInterviewModel().proceedToShowJobRole();
            int jobId = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    jobId = scan.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    ExceptionHandling.printErrorMessage(e.getMessage());
                    addNewCandidate();
                }
            }
            manageCandidateModel.validateUserDetails(name,phoneNo,emailId,address,education,skill,jobId);
    }


    public void showText(String text){
        System.out.println( Color.ANSI_GREEN+
                "\n=================================\n" +
                text  +
                "\n=================================" +
                Color.ANSI_RESET);
    }
    public void showAlert(String alertText){
        System.out.println( Color.ANSI_RED+
                "\n=================================\n" +
                alertText  +
                "\n=================================" +
                Color.ANSI_RESET);
        candidateAddFailed();
    }

    private void candidateAddFailed() {
        System.out.println(
                        "\n=================================" +
                        "\n=  Would You Like To Try Again  =" +
                        "\n=       1.YES      2.NO         =" +
                        "\n================================="
        );
        int choice = ExceptionHandling.getIntInput();
        switch(choice){
            case 1 : addNewCandidate();
                break;
            case 2 : return;
            default:
                System.out.println("Invalid Choice");
                candidateAddFailed();
        }
    }
    public void proceedToShowCandidates(HashMap<Integer, Candidate> candidateHashMap) {
        System.out.println(
                        "\n---------------------------------------------------------------------"+
                        "\nCandidate ID \t\t|  Candidate Name \t\t| Candidate Email  \t|  Candidate Phone \t| Candidate Address \t| Candidate Education \t| Candidate Skill \t| Job Id \t| JobPosition \t| DateOfAttended \t| status"  +
                        "\n---------------------------------------------------------------------"
        );
        for(Map.Entry<Integer,Candidate> entry : candidateHashMap.entrySet()){
            System.out.println(entry.getValue());
        }
        System.out.println("---------------------------------------------------------------------");

    }
}
