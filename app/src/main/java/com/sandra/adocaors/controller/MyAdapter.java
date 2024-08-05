package com.sandra.adocaors.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sandra.adocaors.model.DataClass;
import com.sandra.adocaors.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public void setSearchList(List<DataClass> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<DataClass> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.recLocal.setText(dataList.get(position).getDataLocal());
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recImage.setImageResource(dataList.get(position).getDataImage());
        holder.recDesc.setText(dataList.get(position).getDataDesc());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Local", dataList.get(holder.getAdapterPosition()).getDataLocal());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recLocal, recDesc, recName;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recLocal = itemView.findViewById(R.id.recLocal);
        recDesc = itemView.findViewById(R.id.recDesc);
        recName = itemView.findViewById(R.id.recName);
        recCard = itemView.findViewById(R.id.recCard);
    }
}