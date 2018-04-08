package nekono.inno.hotelki;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 4/7/18.
 */

public class DescriptionDialogFragment extends DialogFragment {
    private double lat;
    private double lng;
    private TextView nameTextView;
    private TextView descriptionTextView;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.description_layout, null);

        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        nameTextView = view.findViewById(R.id.nameTextView);

        this.lat = getArguments().getDouble("lat");
        this.lng = getArguments().getDouble("lng");

        descriptionTextView.setText(lat + " " + lng);


        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                // Add action buttons
                .setPositiveButton("Нравится", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        //TODO add +1 like
                        dismiss();

                    }
                })
                .setNegativeButton("Не нравится", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Dislike
                        //TODO add +1 dislike
                        dismiss();
                    }
                }).create();


        return dialog;
    }


    public void onLikeButton(View view){
        descriptionTextView.setText("HEHEHEH");
      //  dismiss();
    }

    public void onDislikeButton(View view){
        //getDialog().dismiss();
    }
}
