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

public class ReportSchedule extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listschedulenottrue;
    TextView amoutschedulenottrue;
    ArrayAdapter<Schedule> TabListAdapter;
    static ArrayList<Schedule> nontruedschedule;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_schedule);
        GetAllScheduleAndPutAdapter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GetAllScheduleAndPutAdapter() {
        nontruedschedule = new ArrayList<>();
            if(DAO.scheduletab.size()>0){
                for(int i=0;i<DAO.scheduletab.size();i++){
                    if(!DAO.scheduletab.get(i).isStatus()&&!DAO.scheduletab.get(i).isReport()){
                        nontruedschedule.add(DAO.scheduletab.get(i));
                    }
                }
                amoutschedulenottrue=(TextView)findViewById(R.id.amountcanreport);
                amoutschedulenottrue.setText("Not Started Schedule: "+nontruedschedule.size());

                listschedulenottrue =(ListView) findViewById(R.id.listtoreport);
                TabListAdapter = new ArrayAdapter<>(ReportSchedule.this, android.R.layout.simple_list_item_1, nontruedschedule);
                listschedulenottrue.setAdapter(TabListAdapter);
                listschedulenottrue.setOnItemClickListener(this);
            }
    }

    public void backarrow(View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DAO.addreportedschedule(TabListAdapter.getItem(position));
        finish();
    }
}