package id.com.miqdad.alqurandigital.detail;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.com.miqdad.alqurandigital.R;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {

    private List<ResponseDetail> list;

    public DetailAdapter(List<ResponseDetail> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        holder.bindView(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvNomer, tvAyat , tvArti;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);

            tvNomer = itemView.findViewById(R.id.tvNomorAyat);
            tvNama = itemView.findViewById(R.id.tvArab);
            tvAyat = itemView.findViewById(R.id.tvLatin);
            tvArti = itemView.findViewById(R.id.tvArti);
        }

        private void bindView(ResponseDetail data){
            String nama = Html.fromHtml(String.valueOf(data.getTr())).toString();
            tvNomer.setText(data.getNomor());
            tvNama.setText(data.getAr());
            tvAyat.setText(nama);
            tvArti.setText(data.getId());

        }
    }
}
