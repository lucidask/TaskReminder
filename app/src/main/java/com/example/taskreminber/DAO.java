package com.example.taskreminber;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DAO {

    public static long timerstart=0;

    static ArrayList<User> usertab= new ArrayList<>();
    public static void adduser(User user){
        usertab.add(user);
    }

    static ArrayList<Schedule> scheduletab= new ArrayList<>();
    public static void addschedule(Schedule sche){
        scheduletab.add(sche);
    }

    static ArrayList<Schedule> reporteddschedule= new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void addreportedschedule(Schedule sche){
        sche.setReport(true);
        reporteddschedule.add(sche);
    }
}
