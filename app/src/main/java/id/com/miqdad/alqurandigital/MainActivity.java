package id.com.miqdad.alqurandigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.com.miqdad.alqurandigital.model.ResponseList;
import id.com.miqdad.alqurandigital.service.ApiClient;
import id.com.miqdad.alqurandigital.service.BaseApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    AdapterSurah adapterSurah;
    List<ResponseList> list;
    BaseApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        list = new ArrayList<>();
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        apiService = ApiClient.getSurah();
        getDataList();
    }

    private void getDataList(){
        final Call<List<ResponseList>> listCall = apiService.getListSurah();
        listCall.enqueue(new Callback<List<ResponseList>>() {
            @Override
            public void onResponse(Call<List<ResponseList>> call, Response<List<ResponseList>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    adapterSurah = new AdapterSurah(list);
                    rvMain.setAdapter(adapterSurah);
                }else {
                    Toast.makeText(MainActivity.this, "Response Server gagal !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseList>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Bad Internet Conection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
