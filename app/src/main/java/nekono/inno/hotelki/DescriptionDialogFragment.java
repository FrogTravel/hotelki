package nekono.inno.hotelki;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by ekaterina on 4/7/18.
 */

public class DescriptionDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(inflater.inflate(R.layout.description_layout, null))
                // Add action buttons
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Dislike
                    }
                }).create();

        return dialog;
    }


    public void onLikeButton(View view){
        getDialog().dismiss();
    }

    public void onDislikeButton(View view){
        getDialog().dismiss();
    }
}
