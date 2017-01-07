package idi.felixjulen.movieadmin.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import idi.felixjulen.movieadmin.R;

public class EntityViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public ImageView imageView;
    public TextView nameView;

    public EntityViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.imageView = (ImageView) this.itemView.findViewById(R.id.image);
        this.nameView = (TextView) this.itemView.findViewById(R.id.name);
    }
}
