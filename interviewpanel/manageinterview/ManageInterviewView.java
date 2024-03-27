package com.zsgs.chandru.interviewpanel.manageinterview;

import com.zsgs.chandru.interviewpanel.exceptionmessage.ExceptionHandling;
import com.zsgs.chandru.interviewpanel.model.Candidate;
import com.zsgs.chandru.interviewpanel.receptionistcontrol.ReceptionistControlView;
import com.zsgs.chandru.interviewpanel.colortext.Color;
import com.zsgs.chandru.librarymanagement.model.User;

import java.util.*;

public class ManageInterviewView {

    private ManageInterviewModel manageInterviewModel;

    private static Scanner scan;

    public ManageInterviewView(){
        manageInterviewModel = new ManageInterviewModel(this);
        scan = new Scanner(System.in);
    }
    public ManageInterviewModel getInstanceManageInterviewModel(){
        return manageInterviewModel;
    }
     public void initiateManage() {
 whileloop:while (true) {
            System.out.println(
                    "\n\n---------------------------------" +
                            "\n| 1 | Start Interview             |" +
                            "\n| 2 | End Interview               |" +
                            "\n| 3 | Status About Interview      |" +
                            "\n| 4 | Candidate Not Attend        |" +
                            "\n| 5 | Show Job Role               |" +
                            "\n| 6 | Add New Job Role            |" +
                            "\n| 7 | Back                        |" +
                            "\n----------------------------------"
            );
            int choice = ExceptionHandling.getIntInput();
            switch (choice) {
                case 1:
                    manageInterviewModel.proceedToStartOrEnd(2);
                    break;
                case 2:
                    manageInterviewModel.proceedToStartOrEnd(1);
                    break;
                case 3:
                    manageInterviewModel.proceedStatusAboutInterview();
                    break;
                case 4 :
                    candidateNotAttendInterview();
                    break;
                case 5:
                    manageInterviewModel.proceedToShowJobRole();
                    break;
                case 6:
                    addNewJobRole();
                    break;
                case 7:
                    manageInterviewModel.updateDatabase();
                    break whileloop;
                default:
                    System.out.println("Invalid Choice Try Again");
            }
        }
      new ReceptionistControlView().initialControl();
    }



    public void showText(String text){
        System.out.println( Color.ANSI_GREEN+
                "\n=================================\n" +
                text  +
                "\n=================================" +
                Color.ANSI_RESET);
    }

    public int getJobRole(){
        int id = 0;
        manageInterviewModel.proceedToShowCurrJobRole();
        System.out.println("Select the Id");
        try{
            id = scan.nextInt();
        } catch (InputMismatchException e){
            ExceptionHandling.printErrorMessage("Invalid Input");
            scan.next();
            getJobRole();
        }
        return id;
    }

    public void showStatusAboutInterview(ArrayList<Candidate> onProcessInterview){
        System.out.println(
                       "\n---------------------------------------------------------------------"+
                        "\nCandidate Id \t\t| Candidate Name \t\t| Job Id \t\t| Job Name \t\t| Process \t\t| Phone No \t\t| InterviewProcess" +
                        "\n---------------------------------------------------------------------"
        );
        for (Candidate candidate : onProcessInterview){
            System.out.println(candidate.getId()+"\t\t|"+candidate.getName()+"\t\t|"+candidate.getJobId()+"\t\t|"+candidate.getJobPosition()+"\t\t|"+candidate.getProcess()+"\t\t|"+candidate.getPhoneNo()+"\t\t"+candidate.getProcess());
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public void showAllJobRoles(HashMap<Integer,String> roles){
        System.out.println(
                       "\n---------------------------"+
                        "\nRole Id \t\t| Role Name "+
                        "\n--------------------------"
        );
        for(Map.Entry<Integer,String> entry : roles.entrySet()){
            System.out.println(entry.getKey() +"\t\t|"+entry.getValue());
        }
        System.out.println("---------------------------------");
    }
    public void addNewJobRole() {
        manageInterviewModel.proceedToShowJobRole();
        boolean validInput = false;
        int roleId = 0;
        System.out.println("Enter the New Role Id");
        while (!validInput) {
            try {
                roleId = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                ExceptionHandling.printErrorMessage("Input Must Be Integer");
                scan.next();
                addNewJobRole();
            }
        }
        System.out.println("Enter the New Role Name");
        String role = scan.next();
        manageInterviewModel.proceedToAddNewRole(roleId,role);
    }

    public void candidateNotAttendInterview() {
        boolean validInput = false;
        int candidateId = 0;
        System.out.println(
                "\n=================================\n" +
                "\nDid You Remember the Candidate Id" +
                "\n         1.YES       2.NO        " +
                "\n================================="
                );
        int choice = ExceptionHandling.getIntInput();
        switch (choice){
            case 1 : break;
            case 2 : manageInterviewModel.proceedStatusAboutInterview();
                break;
            default:
                System.out.println("Invalid Input");
                candidateNotAttendInterview();
        }
        System.out.println("Enter the New Candidate Id");
        while (!validInput) {
            try {
                candidateId = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                ExceptionHandling.printErrorMessage("Input Must Be Integer");
                scan.next();
                candidateNotAttendInterview();
            }
        }
        manageInterviewModel.proceedCandidateNotAttendInterview(candidateId);
    }
}
