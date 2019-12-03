package id.com.miqdad.alqurandigital;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.com.miqdad.alqurandigital.detail.DetailActivity;
import id.com.miqdad.alqurandigital.model.ResponseList;

public class AdapterSurah extends RecyclerView.Adapter<AdapterSurah.SurahHolder> {

    List<ResponseList> listSurah;

    public AdapterSurah(List<ResponseList> listSurah) {
        this.listSurah = listSurah;
    }

    @NonNull
    @Override
    public SurahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SurahHolder holder, int position) {
        holder.bindView(listSurah.get(position));
    }

    @Override
    public int getItemCount() {
        return listSurah.size();
    }

    public class SurahHolder extends RecyclerView.ViewHolder {
        TextView tvNomer, tvNama, tvJumlah;
        ImageView btSound;
        public SurahHolder(@NonNull View itemView) {
            super(itemView);
            tvNomer = itemView.findViewById(R.id.tvNomorSurah);
            tvNama = itemView.findViewById(R.id.tvNamaSurah);
            tvJumlah = itemView.findViewById(R.id.tvJmlhAyat);



        }

        private void bindView(final ResponseList data){
            tvNomer.setText(data.getNomor());
            tvNama.setText(data.getNama());
            tvJumlah.setText(String.valueOf(data.getAyat()));
            final String nomor = String.valueOf(data.getNomor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailActivity.class)
                    .putExtra("KEY", nomor));
                }
            });

        }
    }
}
