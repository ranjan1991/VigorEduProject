package com.vigoredu.ui.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vigoredu.R;

public class AdapterGridView extends RecyclerView.Adapter<AdapterGridView.MyHolder> {
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);
//        FontUtils.updateFonts(view, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (position == 0) {
            holder.txt_title.setText("Fitness");
            holder.img_view.setBackgroundResource(R.drawable.img_fitness);

        } else if (position == 1) {
            holder.txt_title.setText("Online Education");
            holder.img_view.setBackgroundResource(R.drawable.img_online_education);
        } else if (position == 2) {
            holder.txt_title.setText("Activities");
            holder.img_view.setBackgroundResource(R.drawable.img_activity);
        } else if (position == 3) {
            holder.txt_title.setText("Hospitality");
            holder.img_view.setBackgroundResource(R.drawable.img_hospitality);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        ImageView img_view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            img_view = itemView.findViewById(R.id.img_view);
        }
    }
}
