package com.example.swiftshopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.swiftshopapplication.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.databinding.ActivityMainNavigationBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainNavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainNavigationBinding binding;
    private Button sortingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainNavigation.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_shoppingCart, R.id.nav_profile, R.id.nav_orders, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        sortingButton = findViewById(R.id.button_sort);
        sortingButton.setOnClickListener(v -> {
            if (sortingButton.getText().equals("View in Descending Order")){
                sortingButton.setText("View in Ascending Order");
                HomeFragment.sort(false);
            }
            else {
                sortingButton.setText("View in Descending Order");
                HomeFragment.sort(true);
            }
        });

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        updateHeaderViews(navigationView);
    }


    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_home){
            sortingButton.setVisibility(View.VISIBLE);
        }
        else {
            sortingButton.setVisibility(View.INVISIBLE);
        }
        int id = item.getItemId();
        if (id == R.id.nav_logout) {
            logout();
        } else {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_navigation);
            navController.navigate(id);
        }
        DrawerLayout drawer = binding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void updateHeaderViews(NavigationView navigationView) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        TextView emailTextView = headerView.findViewById(R.id.emailTextView);
        TextView nameTextView = headerView.findViewById(R.id.nameTextView);

        if (user != null) {
            emailTextView.setText(user.getEmail());
            nameTextView.setText(user.getDisplayName());
        } else {
            emailTextView.setText("No Email Available");
            nameTextView.setText("Anonymous");
        }
    }
}