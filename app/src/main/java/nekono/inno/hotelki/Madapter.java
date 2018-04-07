package nekono.inno.hotelki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Madapter extends RecyclerView.Adapter<Madapter.ViewHolder> {

    private ArrayList<HotyolkaItem> dataset;
    private LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View item;
        public TextView name, desc, likes, dizlikes;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout)itemView.findViewById(R.id.ll);
            name = (TextView)itemView.findViewById(R.id.name);
            desc = (TextView)itemView.findViewById(R.id.desc);
            likes = (TextView)itemView.findViewById(R.id.likes);
            dizlikes = (TextView)itemView.findViewById(R.id.dizlikes);
        }
    }

    public Madapter(ArrayList<HotyolkaItem> dataset){
        this.dataset = dataset;
        dataset.add(new HotyolkaItem("Name1", "Desc1", 12, 13));
        dataset.add(new HotyolkaItem("Name2", "Desc2", 9, 0));
        dataset.add(new HotyolkaItem("Name3", "Desc3", 10, 1));
    }

    @Override
    public Madapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Madapter.ViewHolder holder, int position) {
        HotyolkaItem hi = dataset.get(position);
//        holder.name.setText(hi.getName());
//        holder.desc.setText(hi.getDescription());
//        holder.likes.setText(hi.getLikes());
//        holder.dizlikes.setText(hi.getDizlikes());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
