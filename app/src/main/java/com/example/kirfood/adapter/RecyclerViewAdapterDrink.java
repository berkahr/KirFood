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
import com.example.kirfood.Model.DataDrink;
import com.example.kirfood.Model.DataPizza;
import com.example.kirfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerViewAdapterDrink extends FirebaseRecyclerAdapter<DataDrink, RecyclerViewAdapterDrink.MyViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerViewAdapterDrink(@NonNull FirebaseRecyclerOptions<DataDrink> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DataDrink model) {
        holder.name.setText(model.getName());
        holder.description.setText(model.getShortDes());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.drink)
                .error(R.drawable.drink)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DescriptionDrink.class);
                intent.putExtra("DrinkId", "" + position);
                context.startActivity(intent);
            }
        });
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_drink,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView name, description, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgDrinkMenu);
            name = (TextView) itemView.findViewById(R.id.nameDrink);
            description = (TextView) itemView.findViewById(R.id.shortDesDrink);
            price = (TextView) itemView.findViewById(R.id.priceDrink);
        }
    }
}
