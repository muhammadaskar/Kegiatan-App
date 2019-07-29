package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.validasi.DB.AppDbProvider;
import com.askar.validasi.DB.DAO.KegiatanDao;
import com.askar.validasi.DB.Entity.Kegiatan;

public class TambahKegiatan extends AppCompatActivity {

    private EditText editTextNamaKegiatan,
            editTextTanggal, editTextDeskripsi;

    private KegiatanDao kegiatanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kegiatan);

        editTextNamaKegiatan = findViewById(R.id.edt_nama_kegiatan);
        editTextTanggal = findViewById(R.id.edt_tanggal);
        editTextDeskripsi = findViewById(R.id.edt_deskripsi_tambah);

        kegiatanDao = AppDbProvider.databaseKegiatan(getApplicationContext()).kegiatanDao();
    }


    @SuppressLint("StaticFieldLeak")
    private void insertData(final Kegiatan kegiatan){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return kegiatanDao.insertAll(kegiatan);
//                return null;
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(TambahKegiatan.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void clickSimpan(View view) {
        Kegiatan kegiatan;
        String namaKegiatan = editTextNamaKegiatan.getText().toString();
        String tanggal = editTextTanggal.getText().toString();
        String deskripsi = editTextDeskripsi.getText().toString();

        kegiatan = new Kegiatan(namaKegiatan, tanggal, deskripsi);
        insertData(kegiatan);
        startActivity(new Intent(TambahKegiatan.this, ListData.class));
    }
}
