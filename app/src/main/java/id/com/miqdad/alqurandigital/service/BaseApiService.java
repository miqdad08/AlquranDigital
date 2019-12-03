package id.com.miqdad.alqurandigital.service;

import java.util.List;

import id.com.miqdad.alqurandigital.detail.ResponseDetail;
import id.com.miqdad.alqurandigital.model.ResponseList;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface BaseApiService {


    @GET("data.json?print=pretty")
    Call<List<ResponseList>> getListSurah();

    @GET("surat/{nomor}.json?print=pretty")
    Call<List<ResponseDetail>> getDetailSurat(@Path("nomor") String nomor);


}
