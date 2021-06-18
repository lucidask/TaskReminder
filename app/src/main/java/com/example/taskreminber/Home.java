package com.example.taskreminber;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.LocalDateTime;

public class Home extends AppCompatActivity implements View.OnClickListener {
    TextView textwelcome;
    ImageButton gotoAddSchedule;
    ImageButton clockin;
    ImageButton clockout;
    Button minuter;
    Button schedactive;
    String ScheduleTrue;
    Boolean temtrue=false;

    //runnable for chronometre
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            long passedtime = System.currentTimeMillis() - DAO.timerstart;
            int secondpassed = (int) (passedtime / 1000);
            int minutepassed = secondpassed / 60;
            secondpassed = secondpassed % 60;

            minuter.setText(String.format("%d:%02d", minutepassed, secondpassed));
            timerHandler.postDelayed(this, 500);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textwelcome=(TextView)findViewById(R.id.welcome_title);
        textwelcome.setText("WELCOME "+MainActivity.userconnected.get(0).getId().toUpperCase());
        gotoAddSchedule=(ImageButton)findViewById(R.id.schedule);
        minuter=(Button)findViewById(R.id.minuter);
        schedactive=(Button)findViewById(R.id.active_sche);
        clockin=(ImageButton)findViewById(R.id.clockin);
        clockout=(ImageButton)findViewById(R.id.clockout);
        clockout.setOnClickListener(this);
        getclockinstatus();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("********************************BeforeGet-------");
        getclockinstatus();
        System.out.println("********************************AfterGet-------");
    }

    public void launchAddSchedule(View view) {
        Intent AddSchedule= new Intent(getApplicationContext(),AddSchedule.class);
        startActivity(AddSchedule);
    }

    public void logout(View view) {
        finish();
    }

    public void launchClockIn(View view) {
        Intent Clockin= new Intent(getApplicationContext(),ClockIn.class);
        startActivity(Clockin);
    }

    public void launchReported(View view) {
        Intent ReportS= new Intent(getApplicationContext(),ReportedSchedule.class);
        startActivity(ReportS);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getclockinstatus(){
        System.out.println("********************************BeforeFor-------");
        for(int i=0;i<DAO.scheduletab.size();i++) {
            System.out.println("********************************BeforePrimIf-------");
            if (!DAO.scheduletab.get(i).isStatus()) {
                temtrue = false;
            } else if(!DAO.scheduletab.get(i).isUsed()) {
                temtrue = true;
                ScheduleTrue = DAO.scheduletab.get(i).getName();
                break;
            }
            System.out.println("********************************AfterPrimIf-------");
        }

        if(temtrue) {
            System.out.println("********************************temtrue-------");
            if(DAO.timerstart==0){
                System.out.println("********************************temtrue IN-------");
                DAO.timerstart= System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                schedactive.setText("Started Schedule: "+ScheduleTrue);
            }temtrue=false;
        }else
        {
            System.out.println("********************************temfalse-------");
            schedactive.setText("No Started Schedule");
            minuter.setText("00:00");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        for(int i=0;i<DAO.scheduletab.size();i++){
            if(DAO.scheduletab.get(i).isStatus() && !DAO.scheduletab.get(i).isUsed() ) {
                timerHandler.removeCallbacks(timerRunnable);
                DAO.scheduletab.get(i).setReal_end_date(LocalDateTime.now().toString());
                DAO.scheduletab.get(i).setUsed(true);
                schedactive.setText("No Started Schedule");
                minuter.setText("00:00");
                DAO.timerstart=0;
                break;
            }
        }
    }
}