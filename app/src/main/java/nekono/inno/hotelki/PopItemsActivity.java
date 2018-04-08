package nekono.inno.hotelki;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.MapMarker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopItemsActivity extends Activity {
    static List<Idea> ideas = new ArrayList<>();
    static Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_items);

        Api api = Api.retrofit.create(Api.class);

       context = this;

       List<Idea> ideaList = new ArrayList<>();


        Call<List<Idea>> call = api.getIdeas();

        call.enqueue(new Callback<List<Idea>>() {
            @Override
            public void onResponse(Call<List<Idea>> call, Response<List<Idea>> response) {
                Log.d("CLIENTSERVER", response.body().toString());

                List<Idea> idea = response.body();

                PopItemsActivity.ideas = idea;
                PopItemsActivity.drawRecyclerView(idea);
            }

            @Override
            public void onFailure(Call<List<Idea>> call, Throwable t) {
                Log.d("CLIENTSERVER", t.toString());

            }
        });



    }


    static void drawRecyclerView(List<Idea> ideas){
        RecyclerView recyclerView = context.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(new IdeasAdapter(ideas));

    }

}
