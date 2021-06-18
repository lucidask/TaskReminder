package com.example.taskreminber;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText idormail;
    private EditText password;
    private Button signin;
    private Button createaccount;
    private Button forgot;
    static ArrayList<User> userconnected= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idormail=(EditText)findViewById(R.id.editTextTextEmailAddress);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        signin=(Button)findViewById(R.id.connex);
        createaccount=(Button)findViewById(R.id.create);
        forgot=(Button)findViewById(R.id.reset);
        signin.setOnClickListener(this);
        createaccount.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        idormail.getText().clear();
        password.getText().clear();
        if(userconnected.size()!=0){
            userconnected.remove(0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connex:
                boolean confirmed=false;

                DAO.adduser(new User("adm","adm","M",
                        new SimpleDateFormat("00/00/0000"),"adm@adm","adm","adm","Etre un guerrier authentique"));
                String id_email=idormail.getText().toString();
                String pass=password.getText().toString();
                int sizetab=DAO.usertab.size();
                if(!(id_email.isEmpty() || pass.isEmpty())){
                    User userconnect=new User("","","",new SimpleDateFormat(""),
                                                id_email,id_email,pass,"");
                    for (int i=0;i<sizetab;i++){
                        confirmed=userconnect.id_email_password_compare(DAO.usertab.get(i));
                       if(confirmed){
                           userconnected.add(DAO.usertab.get(i));
                           break;
                       }
                    }
                    if(confirmed){
                        Intent HomeP= new Intent(getApplicationContext(),Home.class);
                        startActivity(HomeP);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "User or Password Incorrect",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Fill all in fields",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.create:
                break;
            case R.id.reset:
                break;
        }
    }


}