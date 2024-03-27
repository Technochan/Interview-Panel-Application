package com.zsgs.chandru.interviewpanel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Candidate {
    private String name;
    private String emailId;
    private String phoneNo;
    private String address;
    private String education;
    private String skill;
    private String process;
    private String jobPosition;
    private int jobId;
    private String dateOfInterviewAttended;

    private boolean done ;
    private int id;
    private static int uniqueCandidateId = 100;

    public void setUniqueCandidateId(int id) {
        if(id != 0){
            uniqueCandidateId = id;
        }
    }

    public int getUniqueCandidateId() {
        return uniqueCandidateId;
    }



    public int getId(){
        return id;
    }
    public void setId(){
        id = ++uniqueCandidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }



    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getDateOfInterviewAttended() {
        return dateOfInterviewAttended;
    }

    public void setDateOfInterviewAttended() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateOfInterviewAttended = now.format(formatter);
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return id +"\t\t| "+ name +"\t\t| "+ emailId +"\t\t| "+ phoneNo +"\t\t| "+ address +"\t\t| "+ education +"\t\t| "+ skill +"\t\t| "+jobId+"\t\t| "+ jobPosition+"\t\t| "+dateOfInterviewAttended+" \t|"+process;
    }
}
