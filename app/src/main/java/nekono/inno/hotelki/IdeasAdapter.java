package nekono.inno.hotelki;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekaterina on 4/8/18.
 */

public class IdeasAdapter extends RecyclerView.Adapter<IdeasAdapter.IdeasViewHolder> {

    private List<Idea> ideasList;


    public IdeasAdapter(List<Idea> ideasList) {
        this.ideasList = ideasList;
    }

    @Override
    public IdeasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ideas_layout, parent, false);

        return new IdeasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IdeasViewHolder holder, int position) {
        Idea idea = ideasList.get(position);

        holder.nameTextView.setText(idea.getName().toString());
        if(idea.getApproved() == 0){
            holder.statusTextView.setText("Отказано");
        }else  if(idea.getApproved() == 1){
            holder.statusTextView.setText("Не рассмотрено");
        }else if(idea.getApproved() == 2){
            holder.statusTextView.setText("На рассмотрении");
        }else if(idea.getApproved() == 3){
            holder.statusTextView.setText("Принято");
        }

        holder.statusTextView.setText(String.valueOf(idea.getApproved()));
        holder.descriptionTextView.setText(String.valueOf(idea.getDescription()));
    }

    @Override
    public int getItemCount() {
        return ideasList.size();
    }


    public static class IdeasViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView statusTextView;
        TextView descriptionTextView;


        public IdeasViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.ideaNameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}
