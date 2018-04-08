package nekono.inno.hotelki;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by ekaterina on 4/8/18.
 */

public interface Api {
    @GET("/api/get/markers")
    Call<List<Idea>> getIdeas();



}
