package com.example.dogapp.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.dogapp.MainActivity;
import com.example.dogapp.R;

import com.example.dogapp.model.DogBreed;

import com.example.dogapp.viewmodel.DogAdapter;
import com.example.dogapp.viewmodel.DogsApiService;
import com.example.dogapp.viewmodel.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class List_Fragment extends Fragment implements DogAdapter.OnDogListener{
    private DogsApiService apiService;
    private DogAdapter dogsAdapter;
    private List<DogBreed> dogList;
    private RecyclerView rvDogs;
    private SearchView searchView = null;
    private RecyclerTouchListener touchListener;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu( Menu menu,  MenuInflater inflater) {

        inflater.inflate(R.menu.search_dog, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager=(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if(searchItem!=null){
            searchView= (SearchView) searchItem.getActionView();
        }
        if(searchView!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener=new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                   return  false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    String search = s.toLowerCase(Locale.ROOT);
                    ArrayList<DogBreed> newList = new ArrayList<>();

                    for (DogBreed dog: dogList){
                        if (dog.getName().toLowerCase(Locale.ROOT).contains(search)){
                            newList.add(dog);
                        }
                    }
                    dogsAdapter.updateList(newList);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        binding = FragmentListBinding.inflate(inflater,container,false);
//        View viewRoot = binding.getRoot();
        return inflater.inflate(R.layout.fragment_list_, container, false);
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDogs=view.findViewById(R.id.rv_dogApp);
        dogList=new ArrayList<DogBreed>();
        dogsAdapter=new DogAdapter(dogList,this);
        rvDogs.setAdapter(dogsAdapter);
        rvDogs.setLayoutManager((new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false)));

        apiService=new DogsApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        Log.d("DEBUG","success");
                        for(DogBreed dog: dogBreeds){
                            DogBreed i=new DogBreed(dog.getId(),dog.getName(),dog.getLifeSpan(),dog.getOrigin(),dog.getUrl(),dog.getBreed_for(),dog.getBreed_group(),dog.getTemperament());
                            DogBreed.height j=dog.getHeight();
                            DogBreed.height k=dog.getWeight();
                            i.setHeight(j);
                            i.setWeight(k);
                            Log.d("DEBUG",""+i.getHeight().getImperial());
                            dogList.add(i);
                            dogsAdapter.notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG","Fail"+e.getMessage());
                    }
                });
        touchListener=new RecyclerTouchListener(this.getActivity(),rvDogs);
        touchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {

                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                })
                .setSwipeable(R.id.main_layout, R.id.bottom_wrapper, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {

                    }
                });
        rvDogs.addOnItemTouchListener(touchListener);

    }

    @Override
    public void onDogClick(int position) {
        DogBreed i=dogList.get(position);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dog",i);
        Navigation.findNavController(getView()).navigate(R.id.detail_Fragment,bundle);

    }
}