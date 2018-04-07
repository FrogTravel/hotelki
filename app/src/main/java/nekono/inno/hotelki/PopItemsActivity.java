package nekono.inno.hotelki;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PopItemsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_items);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Idea> ideaList = new ArrayList<>();
        ideaList.add(new Idea("some name 1", "some description", 12, 231));
        ideaList.add(new Idea("some name 2", "some description", 12, 231));
        ideaList.add(new Idea("some name 3", "some description", 12, 231));
        ideaList.add(new Idea("some name 4", "some description", 12, 231));

        recyclerView.setAdapter(new IdeasAdapter(ideaList));

    }


}
