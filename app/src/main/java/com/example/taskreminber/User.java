package com.example.taskreminber;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String fname;
    private String lname;
    private String sex;
    private SimpleDateFormat dateofbirth=new SimpleDateFormat("dd/MM/yyyy");
    private String email;
    private String id;
    private String password;
    private String secretquestion;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public SimpleDateFormat getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(SimpleDateFormat dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretquestion() {
        return secretquestion;
    }

    public void setSecretquestion(String secretquestion) {
        this.secretquestion = secretquestion;
    }

    public User(String fname, String lname, String sex, SimpleDateFormat dateofbirth, String email,
                     String id, String password, String secretquestion){
        this.fname=fname;
        this.lname=lname;
        this.sex=sex;
        this.dateofbirth=dateofbirth;
        this.email=email;
        this.id=id;
        this.password=password;
        this.secretquestion=secretquestion;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean id_email_password_compare(User usertryingtoconnect){
        String email_usertryingtoconnect= usertryingtoconnect.getEmail();
        String id_usertryingtoconnect= usertryingtoconnect.getId();
        String password_usertryingtoconnect= usertryingtoconnect.getPassword();
        String email_this=this.getEmail();
        String id_this=this.getId();
        String password_this= this.getPassword();
        if( (email_usertryingtoconnect.equals(email_this)|| id_usertryingtoconnect.equals(id_this))
                && password_usertryingtoconnect.equals(password_this) ){
            return true;
        }
        else {
            return false;
        }
    }

}
