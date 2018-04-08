package nekono.inno.hotelki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 4/8/18.
 */

public class IdeaAddActivity extends Activity {
    @BindView(R.id.nameEditText) EditText nameEditText;
    @BindView(R.id.descriptionEditText) EditText descriptionEditText;
    @BindView(R.id.tagsEditText) EditText tagsEditText;

    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent receiveIntent = getIntent();
        lat = receiveIntent.getDoubleExtra("lat", 0);
        lng = receiveIntent.getDoubleExtra("lng", 0);

        ButterKnife.bind(this);
    }

    //TODO Pass lat lng
    public void onAdd(View view){
        Idea idea = new Idea(nameEditText.getText().toString(), descriptionEditText.getText().toString(), Arrays.asList(tagsEditText.getText().toString().split(",")), 0,0, lat, lng);

        Log.d("IDEA",idea.getName() + idea.getLat() + idea.getLng());
        //TODO Send POST here
        this.finish();
    }


}
