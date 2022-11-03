package com.example.app_supportpolywork;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout edtEmail,edtSDT, edtHoTen, edtMk, edtMkck;
    Button btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmail = findViewById(R.id.edtEmail);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtMk = findViewById(R.id.edtMatKhau);
        edtMkck = findViewById(R.id.edtMatKhauCk);
        edtSDT = findViewById(R.id.edtSDT);
        btnDK = findViewById(R.id.btnDangKy);
        btnDK.setOnClickListener(v -> {
            if (!validateemail()|!validatename()|!validatepass()|!validatesdt()|!validatrepass()|!booleanvalidatapass()){
                return;
            }else {
                Toast.makeText(this,"Dang Ky Thanh cong !",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean validatename(){
        if(edtHoTen.getEditText().getText().toString().equals("")){
            edtHoTen.setError("Hãy nhập tên của bạn.");
            return false;
        } else{
            edtHoTen.setError(null);
            return true;
        }
    }
    public boolean validateemail(){
        String a="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(edtEmail.getEditText().getText().toString().equals("")){
            edtEmail.setError("Hãy nhập gmail của bạn.");
            return false;
        }else if(!edtEmail.getEditText().getText().toString().matches(a)) {
            edtEmail.setError("Nhập đúng định dạng gmail.");
            return false;
        }else{
            edtEmail.setError(null);
            return true;
        }
    }
    public boolean validatrepass(){
        if(edtMkck.getEditText().getText().toString().equals("")){
            edtMkck.setError("Nhập mật khẩu của bạn");
            return false;
        }else if(!edtMkck.getEditText().getText().toString().matches(edtMk.getEditText().getText().toString())) {
            Log.e("aa",edtMkck.getEditText().getText().toString()+edtMk.getEditText().getText().toString() );
            edtMkck.setError("Mật khẩu không trùng khớp.");
            return false;
        }
        else{
            edtMkck.setError(null);
            return true;
        }
    }
    public boolean booleanvalidatapass(){
        Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = special.matcher(edtMk.getEditText().getText().toString());
        Matcher matcherNumber = number.matcher(edtMk.getEditText().getText().toString());

        boolean constainsSymbols = matcher.find();
        boolean containsNumber = matcherNumber.find();

        if(constainsSymbols == true){
            edtMk.setError("Mật khẩu không có kí tự đặc biệt!");
            return false;
        }
        else if(containsNumber == true){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean validatepass(){
        if(edtMk.getEditText().getText().toString().equals("")){
            edtMk.setError("Nhập mật khẩu của bạn");
            return false;
        }else if(edtMk.getEditText().length()<8) {
            edtMk.setError("Nhập mật khẩu trên 8 kí tự.");
            return false;
        }else if(edtMk.getEditText().getText().toString().replaceAll(" ", "").length() !=edtMk.getEditText().getText().toString().length()){
           edtMk.setError("Mật khẩu không có kí tự đặc biệt!");
            return false;
        }
        else{
            edtMk.setError(null);
            return true;
        }
    }

    public boolean validatesdt(){
        String a = "^0[0-9]{9}$";
        if(edtSDT.getEditText().getText().toString().equals("")){
            edtSDT.setError("Nhập số điện thoại của bạn");
            return false;
        }else if(!edtSDT.getEditText().getText().toString().matches(a)){
            edtSDT.setError("Hãy nhập đúng định dạng số điện thoại!");
            return false;
        }
        else{
            edtSDT.setError(null);
            return true;
        }
    }
}