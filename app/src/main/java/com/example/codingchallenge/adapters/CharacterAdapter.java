package com.example.codingchallenge.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.codingchallenge.model.Character;
import com.example.codingchallenge.R;

import java.util.List;
import java.util.Set;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private List<Character> characterList;
    private OnItemClickListener clickListener;
    private Set<String> favorites;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CharacterAdapter(List<Character> characterList, Set<String> favorites, OnItemClickListener clickListener) {
        this.characterList = characterList;
        this.clickListener = clickListener;
        this.favorites=favorites;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.bind(character);
    }


    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView favoriteTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            favoriteTextView = itemView.findViewById(R.id.favoriteTextView); // Initialize the favorite TextView
        }

        private boolean isFavorite(Character character) {
            if (favorites != null && favorites.contains(character.getName())) {
                return true;
            }
            return false;
        }


        public void bind(Character character) {
            nameTextView.setText(character.getName());
            if (isFavorite(character))
            {
                favoriteTextView.setVisibility(View.VISIBLE);
            }

        }
    }
}


