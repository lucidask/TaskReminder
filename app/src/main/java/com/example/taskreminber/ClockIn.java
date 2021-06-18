package com.example.taskreminber;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClockIn extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listschedule;
    TextView amoutschedule;
    ArrayList<Schedule> Scheduleforclockin = new ArrayList<>();
    ArrayAdapter<Schedule> TabListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_in);
        GetAllScheduleAndPutAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GetAllScheduleAndPutAdapter() {
        if(DAO.scheduletab.size()>0){
            for(int i=0;i<DAO.scheduletab.size();i++){
                if(!DAO.scheduletab.get(i).isStatus()&&!DAO.scheduletab.get(i).isReport()){
                    Scheduleforclockin.add(DAO.scheduletab.get(i));
                }
            }
            amoutschedule=(TextView)findViewById(R.id.amountofschedule);
            amoutschedule.setText("Amount of Schedule: "+Scheduleforclockin.size());

            listschedule =(ListView) findViewById(R.id.clockinschedule);
            TabListAdapter = new ArrayAdapter<>(ClockIn.this, android.R.layout.simple_list_item_1, Scheduleforclockin);
            listschedule.setAdapter(TabListAdapter);
            listschedule.setOnItemClickListener(this);
        }
    }

    public void backarrow(View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TabListAdapter.getItem(position).setStatus(true);
        TabListAdapter.getItem(position).setReal_start_date(LocalDateTime.now().toString());
        finish();
        //            Toast.makeText(ClockIn.this, "Ou klike sou :"+ t,
//            Toast.LENGTH_SHORT).show();

    }
}