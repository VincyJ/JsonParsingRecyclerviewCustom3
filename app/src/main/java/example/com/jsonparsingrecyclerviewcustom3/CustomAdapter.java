package example.com.jsonparsingrecyclerviewcustom3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<ListModel> arrayList = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<ListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View appearance = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(appearance);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListModel listModel = arrayList.get(position);
        holder.rank.setText(String.valueOf(listModel.getRank()));
        holder.country.setText(listModel.getCountryname());
        holder.population.setText(listModel.getPopulation());
        Glide.with(context)
                .load(listModel.getFlag())
                .into(holder.ivFlag);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rank, country, population;
        ImageView ivFlag;

        public MyViewHolder(View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.textView_rank);
            country = itemView.findViewById(R.id.textView_country);
            population = itemView.findViewById(R.id.textView_population);
            ivFlag = itemView.findViewById(R.id.img_flag);
        }
    }
}
