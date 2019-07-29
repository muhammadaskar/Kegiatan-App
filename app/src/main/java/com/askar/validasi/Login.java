package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.validasi.DB.AppDbProvider;
import com.askar.validasi.DB.DAO.UserDao;
import com.askar.validasi.DB.Entity.User;

public class Login extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private Button btnRegister,
            btnLogin;

    public EditText editTextUsername,
            editTextPassword;
    public final static String NAME_SHARED = "shared_prefs";
    private final static String KEEP_LOGIN = "login";
    private UserDao userDao;
    private User user;

    public static final String KUNCI = "kunci";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_click_login);
        btnRegister = findViewById(R.id.btn_intent_register);
        editTextUsername = findViewById(R.id.edt_usename_login);
        editTextPassword = findViewById(R.id.edt_password_login);
        userDao = AppDbProvider.getInstance(getApplicationContext()).userDao();
        sharedPreferences = this.getSharedPreferences(NAME_SHARED, MODE_PRIVATE);
        intentRegister();
        clickLogin();
        autoLogin();
    }

    public void makeAutoLogin(){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putBoolean(KEEP_LOGIN, true);
        editor.apply();
    }

    private void autoLogin(){
        boolean keepLogin = this.sharedPreferences.getBoolean(KEEP_LOGIN, false);

        if (keepLogin){
            startActivity(new Intent(Login.this, ListData.class));
            finish();
        }
    }

    private boolean validasi(){
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        user = userDao.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public void clickLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = editTextUsername.getText().toString();
                if (validasi()){
                    Intent i = new Intent(Login.this, ListData.class);
                    i.putExtra(KUNCI, user);
                    startActivity(i);
                    makeAutoLogin();
                } else {
                    Toast.makeText(Login.this, "Username dan Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static String getKeepLogin() {
        return KEEP_LOGIN;
    }

    private void intentRegister(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}