package com.izadalab.kamus.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dhytodev.mybasemvp.BaseActivity;
import com.izadalab.kamus.R;
import com.izadalab.kamus.ui.dictionary.EngInaFragment;
import com.izadalab.kamus.ui.dictionary.InaEngFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnbinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(getString(R.string.eng_ina_menu));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EngInaFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eng_ina) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new EngInaFragment()).commit();
            getSupportActionBar().setSubtitle(getString(R.string.eng_ina_menu));
        } else if (id == R.id.nav_ina_eng) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new InaEngFragment()).commit();
            getSupportActionBar().setSubtitle(getString(R.string.ina_eng_menu));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
