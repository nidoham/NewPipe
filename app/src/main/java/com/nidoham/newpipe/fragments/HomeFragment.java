package com.nidoham.newpipe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

import com.nidoham.newpipe.fragment.TrendingFragment;
import com.nidoham.newpipe.fragment.LiveFragment;
import org.schabi.newpipe.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeTabAdapter tabAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, 
                           @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        setupViewPager();
        setupTabLayout();
    }
    
    private void setupViewPager() {
        tabAdapter = new HomeTabAdapter(getActivity());
        binding.contentPager.setAdapter(tabAdapter);
        binding.contentPager.setOffscreenPageLimit(1); // Keep both fragments in memory
        binding.contentPager.setUserInputEnabled(false);
    }
    
    private void setupTabLayout() {
        new TabLayoutMediator(binding.tabLayout, binding.contentPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Trending");
                    break;
                case 1:
                    tab.setText("Live");
                    break;
            }
        }).attach();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
    // ViewPager2 Adapter for tabs
    private static class HomeTabAdapter extends FragmentStateAdapter {
        
        public HomeTabAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new TrendingFragment();
                case 1:
                    return new LiveFragment();
                default:
                    return new TrendingFragment();
            }
        }
        
        @Override
        public int getItemCount() {
            return 2; // Trending and Live tabs
        }
    }
}