package com.example.kirfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kirfood.Model.DataPizza;
import com.example.kirfood.Model.DataPopular;
import com.example.kirfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerViewAdapterHome extends FirebaseRecyclerAdapter<DataPopular, RecyclerViewAdapterHome.MyViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewAdapterHome(@NonNull FirebaseRecyclerOptions<DataPopular> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DataPopular model) {
        holder.name.setText(model.getName());
        holder.description.setText(model.getShortDes());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.pizza)
                .error(R.drawable.pizza)
                .into(holder.img);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_popular,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView name, description, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgPopular);
            name = (TextView) itemView.findViewById(R.id.namePopular);
            description = (TextView) itemView.findViewById(R.id.shortDesPopular);
            price = (TextView) itemView.findViewById(R.id.pricepopular);
        }
    }
}
