package com.example.swiftshopapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainMenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SearchView searchView;
    private LinearLayout categoryContainer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        searchView = findViewById(R.id.searchView);
        categoryContainer = findViewById(R.id.categoryContainer);
        navigationView = findViewById(R.id.nav_view);

        setupDrawerContent(navigationView);
        populateCategories();
        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Update search results in real-time
                return false;
            }
        });
    }

    private void populateCategories() {
        // Dynamically add categories to the container
        // You might fetch these from a database or API
    }

    private void setupDrawerContent(NavigationView navigationView) {
        final int homeId = R.id.nav_home;
        final int accountId = R.id.nav_account;
        final int settingsId = R.id.nav_settings;

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case 1000018:
                                // handle click
                                break;
                            case 1000013:
                                // handle click
                                break;
                            case 1000001:
                                // handle click
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }

}
