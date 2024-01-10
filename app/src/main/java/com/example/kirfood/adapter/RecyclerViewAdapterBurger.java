package com.example.kirfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kirfood.DescriptionBurger;
import com.example.kirfood.DescriptionDrink;
import com.example.kirfood.Model.DataBurger;
import com.example.kirfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerViewAdapterBurger extends FirebaseRecyclerAdapter<DataBurger, RecyclerViewAdapterBurger.MyViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewAdapterBurger(@NonNull FirebaseRecyclerOptions<DataBurger> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DataBurger model) {
        holder.name.setText(model.getName());
        holder.description.setText(model.getShortDes());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.burger)
                .error(R.drawable.burger)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DescriptionBurger.class);
                intent.putExtra("BurgerId", "" + position);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_burger,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView name, description, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgBurger);
            name = (TextView) itemView.findViewById(R.id.nameBurger);
            description = (TextView) itemView.findViewById(R.id.shortDesBurger);
            price = (TextView) itemView.findViewById(R.id.priceBurger);
        }
    }
}
