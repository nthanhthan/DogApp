package com.example.dogapp.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.R;

import com.example.dogapp.databinding.FragmentDetailBinding;
import com.example.dogapp.model.DogBreed;

import com.squareup.picasso.Picasso;


public class Detail_Fragment extends Fragment {
    private DogBreed dog;
   private FragmentDetailBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dog=(DogBreed) getArguments().getSerializable("dog");
            Log.d("DEBUG",dog.getName());

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_detail_, null, false);
       View viewRoot=binding.getRoot();
       binding.setDog(dog);
        binding.tvName.setText(dog.getName());
        binding.tvBredFor.setText(dog.getBreed_for());
        binding.tvBreedGroup.setText(dog.getBreed_group());
        binding.tvLifeSpan.setText(dog.getLifeSpan());
        binding.tvTemp.setText(dog.getTemperament());
        binding.tvOrigin.setText(dog.getOrigin());
        binding.tvHeight.setText(dog.getHeight().getImperial());
        binding.tvWeight.setText(dog.getWeight().getImperial());
        Picasso.get().load(Uri.parse(dog.getUrl())).into(binding.imvAvt);
       return viewRoot;
    }
}