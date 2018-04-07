package nekono.inno.hotelki;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ekaterina on 4/8/18.
 */

public class IdeaAddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void onAdd(View view){
        //TODO Send POST here
        this.finish();
    }

}
