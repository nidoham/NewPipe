package com.nidoham.newpipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.schabi.newpipe.MainActivity;
import org.schabi.newpipe.databinding.ActivityOnboardBinding;

public class OnboardActivity extends AppCompatActivity {
    private ActivityOnboardBinding binding;
    private Intent intent = new Intent();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Corrected IDs
        binding.cardNewUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchNextActivity(true);
            }
        });
        binding.cardOldUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchNextActivity(false);
            }
        });
    }
    private void launchNextActivity(final boolean isNew) {
        if (isNew) {
            intent.setClass(getApplicationContext(), MainActivity.class);
        } else {
            intent.setClass(getApplicationContext(), MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
