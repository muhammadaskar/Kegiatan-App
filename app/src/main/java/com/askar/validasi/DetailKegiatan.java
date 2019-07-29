package com.askar.validasi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.askar.validasi.DB.AppDbProvider;
import com.askar.validasi.DB.DAO.KegiatanDao;
import com.askar.validasi.DB.Entity.Kegiatan;

import static com.askar.validasi.ListData.KEY;

public class DetailKegiatan extends AppCompatActivity {

    private EditText editTextNamaKegiatan,
            editTextTanggal, editTextDeskripsi;
    private KegiatanDao kegiatanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);

        editTextNamaKegiatan = findViewById(R.id.edt_nama_kegiatan_detail);
        editTextTanggal = findViewById(R.id.edt_tanggal_detail);
        editTextDeskripsi = findViewById(R.id.edt_deskripsi_detail);

        kegiatanDao = AppDbProvider.databaseKegiatan(getApplicationContext()).kegiatanDao();
        getDetailData();
    }

    private void getDetailData(){
        final Kegiatan kegiatan = (Kegiatan) getIntent().getParcelableExtra(KEY);
        if (kegiatan != null){
            editTextNamaKegiatan.setText(kegiatan.getNamaKegiatan());
            editTextTanggal.setText(kegiatan.getTanggalDanHariKegiatan());
            editTextDeskripsi.setText(kegiatan.getDeskripsi());
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateKegiatan(final Kegiatan kegiatan){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return kegiatanDao.update(kegiatan);
            }

            protected void onPostExecute(Integer status){
                Toast.makeText(DetailKegiatan.this, "Data Berhasil Diuabah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailKegiatan.this, ListData.class));
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteKegiatan(final Kegiatan kegiatan){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                return kegiatanDao.delete(kegiatan);
            }

            protected void onPostExecute(Integer status){
                Toast.makeText(DetailKegiatan.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailKegiatan.this, ListData.class));
            }
        }.execute();
    }




    public void clickEdit(View view) {
        final Kegiatan kegiatan = (Kegiatan) getIntent().getParcelableExtra(KEY);

        kegiatan.setNamaKegiatan(editTextNamaKegiatan.getText().toString());
        kegiatan.setTanggalDanHariKegiatan(editTextTanggal.getText().toString());
        kegiatan.setDeskripsi(editTextDeskripsi.getText().toString());
        updateKegiatan(kegiatan);

    }

    public void clickDelete(View view) {
        final Kegiatan kegiatan = (Kegiatan) getIntent().getParcelableExtra(KEY);
        deleteDialog(view, "Hapus !", "Apakah Anda Yakin ?", kegiatan);
    }

    private void deleteDialog(View mView, String title,CharSequence message, final Kegiatan kegiatan){
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteKegiatan(kegiatan);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();

    }
}
