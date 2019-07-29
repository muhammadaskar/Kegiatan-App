package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.askar.validasi.Adapters.KegiatanAdapter;
import com.askar.validasi.DB.AppDbProvider;
import com.askar.validasi.DB.DAO.KegiatanDao;
import com.askar.validasi.DB.DAO.UserDao;
import com.askar.validasi.DB.Entity.Kegiatan;
import com.askar.validasi.DB.Entity.User;

import java.util.ArrayList;
import java.util.List;

import static com.askar.validasi.Login.KUNCI;

public class ListData extends AppCompatActivity implements KegiatanAdapter.ClickListener{

    private UserDao userDao;
    private User user;

    private RecyclerView recyclerView;
    private KegiatanAdapter kegiatanAdapter;
    private List<Kegiatan> kegiatanList = new ArrayList<>();
    private KegiatanDao kegiatanDao;
    private Kegiatan kegiatan;

    public static final String KEY = "kunci";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        userDao = AppDbProvider.getInstance(getApplicationContext()).userDao();

        recyclerView = findViewById(R.id.rv_kegiatan);
        kegiatanDao = AppDbProvider.databaseKegiatan(getApplicationContext()).kegiatanDao();
        initComponents();
    }

    @SuppressLint("WrongConstant")
    private void initComponents(){
        try{
            kegiatanList = kegiatanDao.getAll();
            kegiatanAdapter = new KegiatanAdapter(kegiatanList);
            kegiatanAdapter.setListener(this);
            recyclerView.setAdapter(kegiatanAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            Toast.makeText(this, "Terjadi Kesalahan Pada" +e, Toast.LENGTH_LONG).show();
        }
    }

    public void clickIntentProfil(View view) {
        String username = getIntent().getStringExtra(KUNCI);
        user = userDao.selectDetailUser(username);
        startActivity(new Intent(ListData.this, Profil.class)
                .putExtra(KUNCI, user));
    }

    public void clickIntentTambah(View view) {
        startActivity(new Intent(ListData.this, TambahKegiatan.class));
    }

    @Override
    public void onClick(View view, int position) {
        kegiatan = kegiatanDao.selectDetail(kegiatanList.get(position).getId());
        startActivity(new Intent(ListData.this, DetailKegiatan.class)
        .putExtra(KEY, kegiatan));
    }
}
