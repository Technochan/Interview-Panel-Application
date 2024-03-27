package com.zsgs.chandru.interviewpanel.login;

import com.zsgs.chandru.interviewpanel.credential.ReceptionistCredential;
import com.zsgs.chandru.interviewpanel.credential.SetupCredential;
import com.zsgs.chandru.interviewpanel.database.InterviewPanelDatabase;
import com.zsgs.chandru.interviewpanel.model.Receptionist;
import com.zsgs.chandru.interviewpanel.validation.InputValidation;


public class LoginModel {

    private LoginView loginView;
    private SetupCredential setupCredential;
    public LoginModel(LoginView loginView){
        this.loginView = loginView;
        setupCredential = new SetupCredential();
    }

    public void checkInitialCredential(String name, String password) {
        if(setupCredential.checkSetupName(name)){
            if(setupCredential.checkSetupPassword(password)){
                loginView.showText("Successfully Logged In \nHurraayyyyy! Welcome :)");
                loginView.createAccount();
            } else {
                loginView.showText("Invalid Password");
                loginView.loginFailed();
            }
        } else {
            loginView.showText("Invalid UserName");
            loginView.loginFailed();
        }
    }

    public void checkReceptionistDetails(String name, String emailId, String phoneNo, String address,String password) {
        if(!InputValidation.getInstance().isValidName(name)){
            loginView.showAlert("Invalid Name \n Name must contains 3 - 30 characters \n Only Alphabets are Allowed)");
            return;
        } else if(!InputValidation.getInstance().isValidEmail(emailId)){
            loginView.showAlert("Invalid Email Id");
            return;
        } else if(!InputValidation.getInstance().isValidPhoneNo(phoneNo)) {
            loginView.showAlert("Invalid Phone No");
            return;
        }  else if(InputValidation.getInstance().isValidAddress(address)){
            loginView.showAlert("Invalid Address");
            return;
        } else if(InputValidation.getInstance().isValidPassword(password)){
            loginView.showAlert("Invalid Password \n Length must be 3 - 30");
            return;
        }
        validCredentials(name,emailId,phoneNo,address,password);
    }
    public void validCredentials(String name, String emailId, String phoneNo, String address,String password) {
        Receptionist receptionist = new Receptionist();
        receptionist.setId();
        receptionist.setName(name);
        receptionist.setEmailId(emailId);
        receptionist.setPhoneNo(phoneNo);
        receptionist.setAddress(address);

        InterviewPanelDatabase.getInstance().insertReceptionistDetails(receptionist.getId(),receptionist);
        InterviewPanelDatabase.getInstance().serializeReceptionistDetail();
        loginView.showText("Your Id: "+receptionist.getId());

        ReceptionistCredential receptionistCredential = new ReceptionistCredential();
        receptionistCredential.setId(receptionist.getId());
        receptionistCredential.setPassword(password);
        InterviewPanelDatabase.getInstance().insertReceptionistCredential(receptionist.getId(), password);
        InterviewPanelDatabase.getInstance().serializeReceptionistCredential();

        loginView.proceedLogin();
    }

    public void checkCredentials(int id, String password) {
       if (InterviewPanelDatabase.getInstance().checkReceptionistCredential(id, password)) {
            loginView.onSuccessComplete();
        } else {
            loginView.showText("Invalid User Id or Password");
            loginView.proceedLogin();
        }
    }
}
