package com.zsgs.chandru.interviewpanel.database;

import com.google.gson.reflect.TypeToken;
import com.zsgs.chandru.interviewpanel.datahandling.DataHandlingHelper;
import com.zsgs.chandru.interviewpanel.model.Candidate;
import com.zsgs.chandru.interviewpanel.model.Receptionist;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class InterviewPanelDatabase {

    private static DataHandlingHelper dataHandlingHelper;
    private static InterviewPanelDatabase interviewPanelDatabase;

    public static InterviewPanelDatabase getInstance(){
        if(interviewPanelDatabase == null){
            interviewPanelDatabase = new InterviewPanelDatabase();
            dataHandlingHelper = new DataHandlingHelper();
        }
        return interviewPanelDatabase;
    }

    // -------------------------------------- Data Structures used to store data start here ---------------------------------
    HashMap<Integer, Receptionist> receptionistDetailsMap = new HashMap<>(); // Receptionist id , Receptionist details
    HashMap<Integer,String> receptionistCredentialsMap = new HashMap<>(); // Receptionist id , Receptionist password
    HashMap<Integer, Candidate> candidateDetailsMap = new HashMap<>(); // Candidate id , Candidate details for only current day
    HashMap<Integer, Candidate> overAllCandidateDetailsMap = new HashMap<>() ;// Candidate id , Candidate details
    HashMap<Integer,String > jobRoles = new HashMap<>(); // Job Id , Job Role
    HashMap<String, Integer> idMaintainMap; // All ID Maintaining
    ArrayList<Candidate> onProcessInterview = new ArrayList<Candidate>(); // Current Day Interview Process Maintain

    // -------------------------------------- Data Structures used to store data End here ---------------------------------



    // ------------------  Data Serialization and DeSerialization process start Here------------------------------

    //Receptionist Details
    public void serializeReceptionistDetail() {
        dataHandlingHelper.serializeData(receptionistDetailsMap, "ReceptionistDetails.txt");
    }
    public void deserializeReceptionistDetailsMap() {
        Type receptionistDetailsType = new TypeToken<HashMap<Integer, Receptionist>>(){}.getType();
        receptionistDetailsMap = dataHandlingHelper.deserializeData("ReceptionistDetails.txt", receptionistDetailsType, HashMap.class);
    }

    // Receptionist Credentials
    public void serializeReceptionistCredential() {
        dataHandlingHelper.serializeData(receptionistCredentialsMap, "ReceptionistCredential.txt");
    }
    public void deserializeReceptionistCredential() {
        Type receptionistCredentialType = new TypeToken<HashMap<Integer, String>>() {}.getType();
        receptionistCredentialsMap = dataHandlingHelper.deserializeData("ReceptionistCredential.txt", receptionistCredentialType, HashMap.class);
    }

    // Candidate Details
    public void serializeOverAllCandidateDetails() {
        dataHandlingHelper.serializeData(overAllCandidateDetailsMap, "candidateDetails.txt");
    }
    public void deserializeCandidateDetails() {
        Type overAllcandidateDetailsType = new TypeToken<HashMap<Integer, Candidate>>() {}.getType();
        overAllCandidateDetailsMap = dataHandlingHelper.deserializeData("candidateDetails.txt", overAllcandidateDetailsType, HashMap.class);
    }

    // Job Roles
    public void serializeJobRoles() {
        dataHandlingHelper.serializeData(jobRoles, "jobRoles.txt");
    }
    public void deserializeJobRoles() {
        Type jobRolesType = new TypeToken<HashMap<Integer, String>>() {}.getType();
        jobRoles = dataHandlingHelper.deserializeData("jobRoles.txt", jobRolesType, HashMap.class);
    }

    // ID Maintaining
    public void serializeIdMaintainLists() {
        dataHandlingHelper.serializeData(idMaintainMap, "idMaintainMap.txt");
    }
    public void deserializeIdMaintainLists() {
        Type idMaintainMapType = new TypeToken<HashMap<String, Integer>>() {}.getType();
        idMaintainMap = dataHandlingHelper.deserializeData("idMaintainMap.txt", idMaintainMapType, HashMap.class);
    }

    // ------------------  Data Serialization and DeSerialization process End Here------------------------------


    // ------------------  Data Retrieve And Update process start Here------------------------------


    // Receptionist Details
    public boolean setupCheck(){
        return receptionistDetailsMap.isEmpty();
    }
    public void insertReceptionistDetails(int id , Receptionist receptionist){
        receptionistDetailsMap.put(id,receptionist);
    }

    // Receptionist Credentials
    public void insertReceptionistCredential(int id,String password){
        receptionistCredentialsMap.put(id,password);
    }
    public boolean checkReceptionistCredential(int id,String password){
        return receptionistCredentialsMap.entrySet().stream()
                .anyMatch(entry -> entry.getKey() == id && entry.getValue().equals(password));
    }

    // Candidate Details
    public HashMap<Integer, Candidate> getCurrDayCandidateDetailsMap(){
        return candidateDetailsMap;
    }
    public HashMap<Integer, Candidate> getOverAllCandidateDetailsMap(){
        return overAllCandidateDetailsMap;
    }
    public void insertCurrDayCandidateDetails(int id,Candidate candidate){
        candidateDetailsMap.put(id,candidate);
    }
    public void insertOverAllCandidateDetails(int id,Candidate candidate){
        overAllCandidateDetailsMap.put(id,candidate);
    }
    public boolean checkExistingCandidateByPhoneNo(String phoneNo) {
        return overAllCandidateDetailsMap.values().stream().anyMatch(user -> user.getPhoneNo().equals(phoneNo));

    }

    // Job Roles
    public boolean isJobRolesEmpty(){
        return jobRoles.isEmpty();
    }
    public void insertNewRole(int id ,String role){
        jobRoles.put(id,role);
    }
    public HashMap<Integer,String >  getJobRoles(){
        return jobRoles;
    }
    public boolean checkJobId(int id){
        return jobRoles.containsKey(id);
    }
    public String getRoleNameById(int id){
        return jobRoles.get(id);
    }


    // Interview Related Status
    public void insertCandidateInterview(Candidate candidate){
        onProcessInterview.add(candidate);
    }
    public ArrayList<Candidate> getOnProcessInterview(){
        return onProcessInterview;
    }
    HashMap<Integer,String > currentDay = new HashMap<>();
    public void insertCurrentDay(int id, String role) {
        currentDay.putIfAbsent(id, role);
    }
    public HashMap<Integer,String > getcurrentDay(){
        return currentDay;
    }


    // Id Maintaining
    public void setUpdatedId(String idType, int id) {
        idMaintainMap.put(idType, id);
    }
    public int getUpdatedId(String idType) {
        return idMaintainMap.getOrDefault(idType, 0);
    }

    // ------------------  Data Retrieve And Update process End Here------------------------------

}
