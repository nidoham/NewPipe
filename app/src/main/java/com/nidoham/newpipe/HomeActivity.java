package com.nidoham.newpipe;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import org.schabi.newpipe.databinding.ActivityHomeBinding;

import org.schabi.newpipe.R;
import com.nidoham.newpipe.fragments.*;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private ViewPagerAdapter viewPagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupViewPager();
        setupBottomNavigation();
    }
    
    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.contentPage.setAdapter(viewPagerAdapter);
        binding.contentPage.setOffscreenPageLimit(1); // Keep all fragments in memory
        binding.contentPage.setUserInputEnabled(false);
        
        // Handle page changes
        binding.contentPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Update bottom navigation selection when page changes
                switch (position) {
                    case 0:
                        binding.bottomNav.setSelectedItemId(R.id.nav_home);
                        break;
                    case 1:
                        binding.bottomNav.setSelectedItemId(R.id.nav_short);
                        break;
                    case 2:
                        binding.bottomNav.setSelectedItemId(R.id.nav_subscription);
                        break;
                    case 3:
                        binding.bottomNav.setSelectedItemId(R.id.nav_me);
                        break;
                }
            }
        });
    }
    
    private void setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_home) {
                binding.contentPage.setCurrentItem(0, true);
                return true;
            } else if (itemId == R.id.nav_short) {
                binding.contentPage.setCurrentItem(1, true);
                return true;
            } else if (itemId == R.id.nav_subscription) {
                binding.contentPage.setCurrentItem(2, true);
                return true;
            } else if (itemId == R.id.nav_me) {
                binding.contentPage.setCurrentItem(3, true);
                return true;
            }
            
            return false;
        });
        
        // Set default selection
        binding.bottomNav.setSelectedItemId(R.id.nav_home);
    }
    
    @Override
    public void onBackPressed() {
        // If not on home tab, go to home tab
        if (binding.contentPage.getCurrentItem() != 0) {
            binding.contentPage.setCurrentItem(0, true);
        } else {
            // If on home tab, exit app
            super.onBackPressed();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    
    // ViewPager2 Adapter
    private static class ViewPagerAdapter extends FragmentStateAdapter {
        
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ShortFragment();
                case 2:
                    return new SubscriptionFragment();
                case 3:
                    return new MeFragment();
                default:
                    return new HomeFragment();
            }
        }
        
        @Override
        public int getItemCount() {
            return 4; // Number of tabs
        }
    }
}