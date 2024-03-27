package com.zsgs.chandru.interviewpanel.login;


import com.zsgs.chandru.interviewpanel.InterviewPanelMain;
import com.zsgs.chandru.interviewpanel.colortext.Color;
import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.exceptionmessage.ExceptionHandling;
import com.zsgs.chandru.interviewpanel.receptionistcontrol.ReceptionistControlView;


import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {

    private LoginModel loginModel;
    private ReceptionistControlView receptionistControlView;
    private static Scanner scan;

    public LoginView(){
        InterviewPanelDatabase.getInstance().deserializeReceptionistCredential();

        loginModel = new LoginModel(this);
        receptionistControlView = new ReceptionistControlView();
        scan = new Scanner(System.in);
    }

    public void initiate() {
        System.out.println("************************************************");
        System.out.println("\t Welcome to "+ InterviewPanelMain.getInstance().getAppName()+"\t\t");
        System.out.println("\t\t\tVersion - "+ InterviewPanelMain.getInstance().getAppVersion()+"\t\t");
        System.out.println("************************************************");
        setupAccount();
    }
    public void showAlert(String alertText){
        System.out.println( Color.ANSI_RED+
                "\n=================================\n" +
                "\t"+alertText  +
                "\n=================================" +
                Color.ANSI_RESET);
        accountCreationFailed();
    }



    public void showText(String text){
        System.out.println( Color.ANSI_GREEN+
                "\n=================================\n" +
                text  +
                "\n=================================" +
                Color.ANSI_RESET);
    }

    public void setupAccount() {
        if(InterviewPanelDatabase.getInstance().setupCheck()){
            proceedSetupAccount();
        } else {
            proceedLogin();
        }
    }

    public void proceedSetupAccount() {
        System.out.println("\n\nEnter the Pre-Installed UserName :");
        String name = scan.next();
        System.out.println("\nEnter the Pre-Installed Password :");
        String password = scan.next();
        loginModel.checkInitialCredential(name,password);
    }

    public void createAccount() {
        try {
            System.out.println("\nEnter Your Name:");
            String name = scan.next();

            System.out.println("Enter Your Email Id:");
            String emailId = scan.next();

            System.out.println("Enter Your Phone No:");
            String phoneNo = scan.next();

            System.out.println("Enter Your Address:");
            String address = scan.next();

            System.out.println("Enter Your Password:");
            String password = scan.next();

            loginModel.checkReceptionistDetails(name, emailId, phoneNo, address,password);
        } catch (Exception e) {
            ExceptionHandling.printErrorMessage(e.getMessage());
            accountCreationFailed();
        }
    }
    public void loginFailed() {
        int choice;
        do {
            System.out.println(
                    "\n=================================" +
                            "\n=  Would You Like To Try Again  =" +
                            "\n=       1.YES      2.NO         =" +
                            "\n================================="
            );
            choice = ExceptionHandling.getIntInput();
            switch (choice) {
                case 1:
                    proceedSetupAccount();
                    break;
                case 2:
                    initiate();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 1 && choice != 2);
    }
    public void proceedLogin() {
      int id;
        boolean validInput = false;
        do {
            System.out.println("\n\nEnter your Receptionist Id :\n---------------------");
            id = ExceptionHandling.getIntInput();
            if (id != 0) {
                validInput = true;
            }
        } while (!validInput);

        System.out.println("Enter your password :\n---------------------");
        String password = scan.next();
        loginModel.checkCredentials(id, password);
    }



    private void accountCreationFailed() {
        int choice;
        do {
            System.out.println(
                    "\n=================================" +
                            "\n=  Would You Like To Try Again  =" +
                            "\n=       1.YES      2.NO         =" +
                            "\n================================="
            );
            choice = ExceptionHandling.getIntInput();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    initiate();
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 1 && choice != 2);
    }

    public void onSuccessComplete() {
        receptionistControlView.initialControl();
    }
}
