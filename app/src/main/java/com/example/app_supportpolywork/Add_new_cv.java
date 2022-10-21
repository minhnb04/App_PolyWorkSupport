package com.example.app_supportpolywork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Add_new_cv extends AppCompatActivity {
    ImageButton btn_1,btn_2,btn_3,btn_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cv);
        btn_1 =  findViewById(R.id.btnAddPersonall);
        btn_2 =  findViewById(R.id.btnAddeducation);
        btn_3 =  findViewById(R.id.btnAddexperience);
        btn_4 =  findViewById(R.id.btnAddskill);
        btn_1.setOnClickListener(v -> {
            add();
        });
        btn_2.setOnClickListener(v -> {
            add2();
        });
        btn_3.setOnClickListener(v -> {
                add3();
        });
        btn_4.setOnClickListener(v -> {
            add4();
        });
    }
    private void add() {
        LayoutInflater inflater = LayoutInflater.from(this) ;
         View v = inflater.inflate(R.layout.add_item_cv_1, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edTen = v.findViewById(R.id.edName);
        EditText edpos = v.findViewById(R.id.edPosition);
                builder.setView(v);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), edTen.getText().toString() + "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        }); builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
            builder.create();
            builder.show();
    }
    private void add2() {
        LayoutInflater inflater = LayoutInflater.from(this) ;
        View v = inflater.inflate(R.layout.add_item_cv_2, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edTen = v.findViewById(R.id.edSchoolName);
        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), edTen.getText().toString() + "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        }); builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }
    private void add3() {
        LayoutInflater inflater = LayoutInflater.from(this) ;
        View v = inflater.inflate(R.layout.add_item_cv_3, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edTen = v.findViewById(R.id.edCompanyName);

        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), edTen.getText().toString() + "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        }); builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }
    private void add4() {
        LayoutInflater inflater = LayoutInflater.from(this) ;
        View v = inflater.inflate(R.layout.add_item_cv_4, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText edTen = v.findViewById(R.id.edSkillName);
        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), edTen.getText().toString() + "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        }); builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }
    private  void  showProgress(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setView(R.layout.custom_progres)
                    builder.setTitle("Loading ... ");
                    builder.setCancelable(true);
                    builder.show();
                }catch(Exception e) {
                }
            }
        }, 4000);


    }
}