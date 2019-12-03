package id.com.miqdad.alqurandigital.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.com.miqdad.alqurandigital.R;
import id.com.miqdad.alqurandigital.service.ApiClient;
import id.com.miqdad.alqurandigital.service.BaseApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    RecyclerView rvDetail;
    BaseApiService apiService;
    List<ResponseDetail> data;
    DetailAdapter detailAdapter;
    String nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        rvDetail = findViewById(R.id.rvDetail);
        apiService = ApiClient.getSurah();
        data = new ArrayList<>();
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        nomor = getIntent().getStringExtra("KEY");
        getDetailSurah(nomor);

    }

    private void getDetailSurah(String no){
        final ProgressDialog loading = ProgressDialog.show(this, null, "loading...", false,false);
        Call <List<ResponseDetail>>listCall = apiService.getDetailSurat(no);
        listCall.enqueue(new Callback<List<ResponseDetail>>() {
            @Override
            public void onResponse(Call<List<ResponseDetail>> call, Response<List<ResponseDetail>> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    data = response.body();
                    detailAdapter = new DetailAdapter(data);
                    rvDetail.setAdapter(detailAdapter);
                }else {
                    loading.dismiss();
                    Toast.makeText(DetailActivity.this, "Response Failed", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<List<ResponseDetail>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetailActivity.this, "bad conection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
