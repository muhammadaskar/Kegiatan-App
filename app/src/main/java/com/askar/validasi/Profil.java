package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.askar.validasi.DB.Entity.Kegiatan;
import com.askar.validasi.DB.Entity.User;
import static com.askar.validasi.Login.KUNCI;
import static com.askar.validasi.Login.NAME_SHARED;

public class Profil extends AppCompatActivity {

    private EditText editTextUsername,
                    editTextEmail;
    private Login login;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        editTextUsername = findViewById(R.id.username_profil);
        editTextEmail = findViewById(R.id.email_profil);
        sharedPreferences = this.getSharedPreferences(NAME_SHARED, MODE_PRIVATE);
        getDetailProfil();
    }

    private void getDetailProfil(){
        final User user = (User) getIntent().getParcelableExtra(KUNCI);
        if (user != null){
            editTextUsername.setText(user.getUsername());
            editTextEmail.setText(user.getEmail());
        }
    }


    public void clickLogout(View view) {
        exitDialog(view, "keluar", "Apakah Anda Yakin ?");
    }

    private void makeNotAutoLogin(){
        login = new Login();
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.remove(login.getKeepLogin());
        editor.apply();
    }

    private void exitDialog(View mView, String title,CharSequence message){
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makeNotAutoLogin();
                        startActivity(new Intent(Profil.this, Login.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();

    }
}
