package nekono.inno.hotelki;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ekaterina on 4/8/18.
 */

public interface Api {
    @GET("/api/get/markers")
    Call<List<Idea>> getIdeas();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.91.50.11:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
