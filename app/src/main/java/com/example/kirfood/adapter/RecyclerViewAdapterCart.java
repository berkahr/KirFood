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
import com.example.kirfood.DescriptionDrink;
import com.example.kirfood.Model.DataCart;
import com.example.kirfood.Model.DataDrink;
import com.example.kirfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerViewAdapterCart extends FirebaseRecyclerAdapter<DataCart, RecyclerViewAdapterCart.MyViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewAdapterCart(@NonNull FirebaseRecyclerOptions<DataCart> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull RecyclerViewAdapterCart.MyViewHolder holder, int position, @NonNull DataCart model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
//        final DataDrink local = model;
//        holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent foodDetails = new Intent(v.getContext(), DescriptionDrink.class);
//                foodDetails.putExtra("name",adapter.getRef(position).getKey());
//            }
//        });
    }
    @NonNull
    @Override
    public RecyclerViewAdapterCart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart,parent,false);
        return new RecyclerViewAdapterCart.MyViewHolder(view);
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameFoodCart);
            price = (TextView) itemView.findViewById(R.id.priceFoodCart);
        }
    }
}