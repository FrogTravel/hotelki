package nekono.inno.hotelki;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ekaterina on 4/7/18.
 */

public class EditMarkerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
    }


    public void onShowDialog(View view){
        DialogFragment dialogFragment = new DescriptionDialogFragment();
        dialogFragment.show(getFragmentManager(), "mysupertag");

//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.description_layout);
//
//        dialog.show();
    }



}
