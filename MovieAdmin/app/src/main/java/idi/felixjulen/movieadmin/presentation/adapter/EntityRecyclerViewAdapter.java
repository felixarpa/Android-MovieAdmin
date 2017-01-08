package idi.felixjulen.movieadmin.presentation.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FileManager;
import idi.felixjulen.movieadmin.domain.model.Entity;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;


public class EntityRecyclerViewAdapter<T extends EntityViewHolder, S extends Entity> extends RecyclerView.Adapter<T> {

    private Integer rowLayoutResourceId;
    protected ArrayList<S> data;
    private OnRecyclerViewItemAction callback;
    protected Context context;

    public EntityRecyclerViewAdapter(Integer rowLayoutResourceId, ArrayList<S> data, OnRecyclerViewItemAction callback) {
        this.rowLayoutResourceId = rowLayoutResourceId;
        this.data = data;
        this.callback = callback;
    }

    public EntityRecyclerViewAdapter(Integer rowLayoutResourceId, ArrayList<S> data, OnRecyclerViewItemAction callback, Context context) {
        this.rowLayoutResourceId = rowLayoutResourceId;
        this.data = data;
        this.callback = callback;
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(rowLayoutResourceId, parent, false);
        return newT(view);
    }

    protected T newT(View view) {
        //noinspection unchecked
        return (T) new EntityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        final Integer itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onRecyclerViewItemClick(data.get(itemPosition).getId());
                }
        });
        holder.nameView.setText(data.get(position).getName());
        FileManager fm = FileManager.getInstance(context);
        Bitmap image = fm.loadImageFromStorage(data.get(position).getImage(), R.mipmap.profile);
        if (image != null) {
            holder.imageView.setImageBitmap(image);
        }
    }

    @Override
    public void onViewRecycled(T holder) {
        holder.itemView.setOnClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
