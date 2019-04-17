package com.sxl.mvpmoduledemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sxl.mvpmoduledemo.R;
import com.sxl.mvpmoduledemo.contract.LoginContract;
import com.sxl.mvpmoduledemo.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private EditText edtTxtLoginAccount;
    private EditText edtTxtLoginPassword;
    private Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtTxtLoginAccount = findViewById(R.id.edtTxt_login_account);
        edtTxtLoginPassword = findViewById(R.id.edtTxt_login_password);
        btnLogin = findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(this);
        loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public String getName() {
        return edtTxtLoginAccount.getText().toString();
    }

    @Override
    public String getPassWord() {
        return edtTxtLoginPassword.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_login_login){
            // 通知展示器
            loginPresenter.login();
        }
    }
}
