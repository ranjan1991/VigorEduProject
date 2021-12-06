package com.vigoredu.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.vigoredu.R;
import com.vigoredu.databinding.FragmentHomeBinding;
import com.vigoredu.ui.home.adapters.AdapterGridView;
import com.vigoredu.ui.home.adapters.SliderAdapterExample;
import com.vigoredu.ui.home.beans.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    SliderView sliderView;
    private SliderAdapterExample adapter;
    private RecyclerView grid_recycler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        grid_recycler = binding.gridRecycler;
       /* final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        setupGridView();
        setupSliderAdapter();
        renewItems();
        return root;
    }

    private void setupSliderAdapter() {
        sliderView = binding.imageSlider;
        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    //============= Grid View Setup ====================
    private void setupGridView() {
        grid_recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AdapterGridView adapterGridView = new AdapterGridView();
        grid_recycler.setAdapter(adapterGridView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("");
        sliderItem.setImageUrl("https://secureservercdn.net/160.153.137.99/2vl.bb9.myftpupload.com/wp-content/uploads/2021/12/Copy-of-Online-Yoga-Fitness-Class-Facebook-Banner-Made-with-PosterMyWall-2.jpg?time=1638703295");
        sliderItemList.add(sliderItem);

        SliderItem sliderItem1 = new SliderItem();
        sliderItem1.setDescription("");
        sliderItem1.setImageUrl("https://secureservercdn.net/160.153.137.99/2vl.bb9.myftpupload.com/wp-content/uploads/2021/12/Copy-of-Online-Classes-Twitter-header-post-Made-with-PosterMyWall-1.png?time=1638703295");
        sliderItemList.add(sliderItem1);

        SliderItem sliderItem2 = new SliderItem();
        sliderItem2.setDescription("");
        sliderItem2.setImageUrl("https://secureservercdn.net/160.153.137.99/2vl.bb9.myftpupload.com/wp-content/uploads/2021/12/Copy-of-Digital-Marketing-Agency-Ad-Made-with-PosterMyWall.jpg?time=1638703295");
        sliderItemList.add(sliderItem2);

        adapter.renewItems(sliderItemList);
    }

}