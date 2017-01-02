package idi.felixjulen.movieadmin.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemClick;


public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterRecyclerViewAdapter.AdapterViewHolder> {

    private ArrayList<Character> characters;
    private OnRecyclerViewItemClick callback;

    public CharacterRecyclerViewAdapter(ArrayList<Character> characters, OnRecyclerViewItemClick callback) {
        this.characters = characters;
        this.callback = callback;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_row_layout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        final Integer itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onRecyclerViewItemClick(itemPosition, characters.get(itemPosition).getId());
                }
        });
        holder.nameView.setText(characters.get(position).getName());
        holder.imageView.setImageBitmap(characters.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private ImageView imageView;
        private TextView nameView;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageView = (ImageView) this.itemView.findViewById(R.id.image);
            this.nameView = (TextView) this.itemView.findViewById(R.id.name);
        }
    }
}
