package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.validasi.DB.AppDbProvider;
import com.askar.validasi.DB.DAO.UserDao;
import com.askar.validasi.DB.Entity.User;

public class Register extends AppCompatActivity {

    private EditText editTextUsername,
            editTextEmail,
            editTextPassword,
            editTextRePassword;
    private UserDao userDao;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.edt_usename_register);
        editTextEmail = findViewById(R.id.edt_email_regiter);
        editTextPassword = findViewById(R.id.edt_password_register);
        editTextRePassword = findViewById(R.id.edt_ulang_password_register);

        userDao = AppDbProvider.getInstance(getApplicationContext()).userDao();

    }

    private void insertRegister(final User user){
        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                return userDao.insertRegister(user);
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, Login.class));
            }
        }.execute();
    }

    public void clickRegister(View view) {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String rePassword = editTextRePassword.getText().toString();
        user = new User(username, email, password, rePassword);

        if (username.isEmpty() && email.isEmpty() && password.isEmpty() && rePassword.isEmpty()){
            Toast.makeText(this, "Silahkan Mengisi Data", Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            Toast.makeText(this, "Silahkan Mengisi Data", Toast.LENGTH_SHORT).show();
        } else if (isValidEmail(email)){
            Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6){
            Toast.makeText(this, "Digit Password Terlalu Pendek", Toast.LENGTH_SHORT).show();
        } else if (!rePassword.equals(password)){
            Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
        } else{
            insertRegister(user);
        }
    }

    private boolean isValidEmail(String email){
        return (Patterns.EMAIL_ADDRESS.matcher(email)).matches();
    }
}
