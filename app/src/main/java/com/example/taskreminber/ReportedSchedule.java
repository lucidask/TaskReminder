package com.example.taskreminber;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ReportedSchedule extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView amount;
    ListView listreported;
    ArrayAdapter<Schedule> TabListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_schedule);
        getreportedSchedule();
    }

    public void getreportedSchedule(){
        if(DAO.reporteddschedule.size()>0){
            amount=(TextView)findViewById(R.id.amountreported);
            amount.setText("Reported Schedule: "+DAO.reporteddschedule.size());
            listreported =(ListView) findViewById(R.id.schedulereportedlist);
            TabListAdapter = new ArrayAdapter<>(ReportedSchedule.this, android.R.layout.simple_list_item_1, DAO.reporteddschedule);
            listreported.setAdapter(TabListAdapter);
        }
        else {
            amount=(TextView)findViewById(R.id.amountreported);
            amount.setText("Empty");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getreportedSchedule();

    }

    public void backarrow(View view) {
        finish();
    }

    public void LaunchReportSchedule(View view) {
        Intent ReportS= new Intent(getApplicationContext(),ReportSchedule.class);
        startActivity(ReportS);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}