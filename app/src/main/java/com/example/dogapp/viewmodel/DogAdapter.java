package com.example.dogapp.viewmodel;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public  class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<DogBreed> dogsList;
    private OnDogListener onDogListener;

    public DogAdapter(List<DogBreed> dogsList,OnDogListener onDogListener){
        this.dogsList=dogsList;
        this.onDogListener=onDogListener;

    }
    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);

        return new ViewHolder(view,onDogListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        DogBreed dogBreed = dogsList.get(position);

        holder.tvDogName.setText(dogBreed.getName());
        holder.tvDogBred.setText(dogBreed.getBreed_for());
        Picasso.get().load(dogBreed.getUrl()).into(holder.ivDogImage);

        holder.tvNameSwipe.setText(dogBreed.getName());
        holder.tvOriginSwipe.setText(dogBreed.getOrigin());
        holder.tvLifeSpan.setText(dogBreed.getLifeSpan());
        holder.tvTemperament.setText(dogBreed.getTemperament());

    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivDogImage;
        private TextView tvDogName, tvDogBred;
        private TextView tvNameSwipe, tvOriginSwipe, tvLifeSpan, tvTemperament;
        OnDogListener onDogListener;
        public ViewHolder(View view,OnDogListener onDogListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            ivDogImage = view.findViewById(R.id.iv_dogImage);
            tvDogName = view.findViewById(R.id.tv_dogName);
            tvDogBred = view.findViewById(R.id.tv_dogBred);

            tvNameSwipe = view.findViewById(R.id.tv_nameSwipe);
            tvOriginSwipe = view.findViewById(R.id.tv_originSwipe);
            tvLifeSpan = view.findViewById(R.id.tv_lifeSpanSwipe);
            tvTemperament = view.findViewById(R.id.tv_temperamentSwipe);
            this.onDogListener=onDogListener;
            view.setOnClickListener(this);
            // textView = (TextView) view.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            onDogListener.onDogClick(getAdapterPosition());

        }


//        public TextView getTextView() {
//            return textView;
//        }
    }
    public interface OnDogListener{
        void onDogClick(int position);
    }
    public void updateList(ArrayList<DogBreed> newList){
        dogsList = new ArrayList<>();
        dogsList.addAll(newList);
        notifyDataSetChanged();
    }

}
