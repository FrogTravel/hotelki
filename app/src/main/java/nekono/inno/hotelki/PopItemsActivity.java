package nekono.inno.hotelki;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PopItemsActivity extends Activity {
    private RecyclerView your_face;
    private RecyclerView.Adapter my_balls;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_items);

        your_face = (RecyclerView) findViewById(R.id.rekyckler);

        my_balls = new Madapter(new ArrayList<HotyolkaItem>());
        your_face.setAdapter(my_balls);

        layoutManager = new LinearLayoutManager(this);
        your_face.setLayoutManager(layoutManager);

    }


}
