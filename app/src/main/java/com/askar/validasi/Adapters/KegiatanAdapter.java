package com.askar.validasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.askar.validasi.DB.Entity.Kegiatan;
import com.askar.validasi.R;

import java.util.List;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.MyViewholder> {

    private Context context;
    private List<Kegiatan> kegiatanList;
    private ClickListener listener;

    public interface ClickListener{
        public void onClick(View view, int position);
    }

    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    public KegiatanAdapter(List<Kegiatan> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kegiatan, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Kegiatan kegiatan = kegiatanList.get(position);
        holder.textViewNamaKegiatan.setText(kegiatan.getNamaKegiatan());
        holder.textViewWaktu.setText(kegiatan.getTanggalDanHariKegiatan());
    }

    @Override
    public int getItemCount() {
        return kegiatanList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textViewNamaKegiatan,
                textViewWaktu;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            textViewNamaKegiatan = itemView.findViewById(R.id.tv_nama_kegiatan);
            textViewWaktu = itemView.findViewById(R.id.tv_waktu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
