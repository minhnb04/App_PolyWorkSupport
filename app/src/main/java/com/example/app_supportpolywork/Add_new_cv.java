package com.example.app_supportpolywork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_supportpolywork.model.ThongTinCaNhan_Add_CV_test;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Add_new_cv extends AppCompatActivity {
    ImageButton btn_1, btn_2, btn_3, btn_4;
    Button btnUp;
    int SELECT_IMAGE = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cv);
        btn_1 = findViewById(R.id.btnAddPersonall);
        btn_2 = findViewById(R.id.btnAddeducation);
        btn_3 = findViewById(R.id.btnAddexperience);
        btn_4 = findViewById(R.id.btnAddskill);


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
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_item_cv_thongtincanhan, null);
        imageView = findViewById(R.id.img_detail_cv);
        btnUp=findViewById(R.id.btnUpImagePer);
        EditText edTen = v.findViewById(R.id.edHoTen);
        EditText edNghe = v.findViewById(R.id.edNgheNghiep);
        EditText edGoiTinh = v.findViewById(R.id.edGioitinh);
        EditText edDiachi = v.findViewById(R.id.edDiachi);
        EditText edEmail = v.findViewById(R.id.edEmail);
        EditText edSdt = v.findViewById(R.id.edSdt);
        EditText edNamSinh = v.findViewById(R.id.edNgaySinh);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
//                    btnUp.setOnClickListener(v -> {
//                        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(Intent.ACTION_PICK);
//                        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intent,SELECT_IMAGE);
//                    });
                    ThongTinCaNhan_Add_CV_test modelTest = new ThongTinCaNhan_Add_CV_test(edTen.getText().toString()
                            , edNghe.getText().toString()
                            , edGoiTinh.getText().toString()
                            , edDiachi.getText().toString()
                            , edEmail.getText().toString()
                            , Integer.parseInt(edSdt.getText().toString())
                            , edNamSinh.getText().toString()

                    );
                    Intent intent = new Intent(getApplicationContext(), CV_Detail.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",modelTest);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }

    private void add2() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_item_cv_vitri, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }

    private void add3() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_item_cv_kinang, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }

    private void add4() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.add_item_cv_hocvan, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    showProgress();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                }

            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });
        builder.create();
        builder.show();
    }

    private void showProgress() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Loading ... ");
                    builder.setCancelable(true);
                    builder.show();
                } catch (Exception e) {
                }
            }
        }, 4000);


    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK) {
//           if (requestCode == SELECT_IMAGE){
//               imageView.setImageURI(data.getData());
//           }
//
//        }
//    }
}