package com.example.taskreminber;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddSchedule extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    ImageButton back;
    Button save;
    EditText sche_name;
    EditText expect_st;
    EditText expect_en;
    EditText sche_note;
    String date_to_ondateset="";
    String time_to_ontimeset="";
    String temporation="";
    boolean st=false;
    boolean ed=false;
    TextView amoutschedule;
    ListView listschedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        back=(ImageButton)findViewById(R.id.backarrow);
        sche_name=(EditText)findViewById(R.id.sname);
        expect_st=(EditText)findViewById(R.id.expected_start);
        expect_en=(EditText)findViewById(R.id.expected_end);
        sche_note=(EditText)findViewById(R.id.note);
        expect_st.setOnFocusChangeListener(this);
        expect_en.setOnFocusChangeListener(this);
        save=(Button)findViewById(R.id.save_sche);
        save.setOnClickListener(this);
        listschedule=(ListView)findViewById(R.id.schedulesave);
    }

    @Override
    protected void onResume() {
        super.onResume();
        amoutschedule=(TextView)findViewById(R.id.amountofschedulesaved);
        amoutschedule.setText("Schedule Saved: "+DAO.scheduletab.size());
        ArrayAdapter ScheduleTabConnect = new ArrayAdapter(
                AddSchedule.this, android.R.layout.simple_list_item_1,DAO.scheduletab);
        listschedule.setAdapter(ScheduleTabConnect);
    }

    public void backarrow(View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        String n=sche_name.getText().toString();
        String st=expect_st.getText().toString();
        String ed=expect_en.getText().toString();
        String not=sche_note.getText().toString();
        if(!(n.isEmpty() && st.isEmpty() && ed.isEmpty())){
            DAO.addschedule(new Schedule(n,st,ed,"Not Yet Started","Not Yet Finished",not,false,false,false));
            sche_name.getText().clear();
            expect_st.getText().clear();
            expect_en.getText().clear();
            sche_note.getText().clear();
            amoutschedule=(TextView)findViewById(R.id.amountofschedulesaved);
            amoutschedule.setText("Schedule Saved: "+DAO.scheduletab.size());
            ArrayAdapter ScheduleTabConnect = new ArrayAdapter(
                    AddSchedule.this, android.R.layout.simple_list_item_1,DAO.scheduletab);
            listschedule.setAdapter(ScheduleTabConnect);
            Toast.makeText(AddSchedule.this,"Schedule: "+n+" is saved",
            Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(AddSchedule.this,"Fill in the first 3 fields" ,
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(hasFocus){
            if(v.getId()==R.id.expected_start){
                st=true;
                ed=false;
            }else
                st=false;

            if(v.getId()==R.id.expected_end){
                ed=true;
                st=false;
            }else
                ed=false;

            temporation="";
            Calendar cal= Calendar.getInstance();
            int nowhour=cal.get(Calendar.HOUR_OF_DAY);
            int nowminutes=cal.get(Calendar.MINUTE);
            int nowyear=cal.get(Calendar.YEAR);
            int nowmonth=cal.get(Calendar.MONTH);
            int nowday=cal.get(Calendar.DAY_OF_MONTH);


            TimePickerDialog pickerhour = new TimePickerDialog(AddSchedule.this,
                    AlertDialog.THEME_HOLO_LIGHT,this, nowhour, nowminutes, true);
            pickerhour.show();

            DatePickerDialog pickerdate= new DatePickerDialog(AddSchedule.this,
                    AlertDialog.THEME_HOLO_LIGHT,this, nowyear, nowmonth, nowday);
            pickerdate.show();

        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time_to_ontimeset = hourOfDay + ":" + minute;
        if(st){
            temporation+=time_to_ontimeset;
            expect_st.setText(temporation);
        }
        if(ed){
            temporation+=time_to_ontimeset;
            expect_en.setText(temporation);
        }
        System.out.println("*****************************************************************************:   "+temporation);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date_to_ondateset = dayOfMonth + "/" + month + "/" + year+" ";
//        expect_st.setText(expect_st.getText().toString()+date_to_ondateset);

        if(st){
            temporation+=date_to_ondateset;
            expect_st.setText(temporation);
        }
        if(ed){
            temporation+=date_to_ondateset;
            expect_en.setText(temporation);
        }
        System.out.println("##################################################################################:   "+temporation);
    }
}