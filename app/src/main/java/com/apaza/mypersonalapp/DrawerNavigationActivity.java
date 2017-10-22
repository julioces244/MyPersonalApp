package com.apaza.mypersonalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DrawerNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = DrawerNavigationActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;

    private TextView usernameText;
    private TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        usernameText = (TextView)findViewById(R.id.fullname_text);
        prueba = (TextView)findViewById(R.id.textView3);
        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

       List<User> users = UserRepository.list();
       for (User user : users) {
            //User user = UserRepository.getUser(username);

            //usernameText.setText(username);
        usernameText.setText(user.getNombres());

           TextView fullnameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
           fullnameText.setText(user.getUsusario());




       }


        int color, textSize;
        String ola;
        boolean usingPreferences = sharedPreferences.getBoolean("cbxPreferenceOn", false);
        if (usingPreferences) {
            // Recogemos el texto.
            ola = sharedPreferences.getString("etpTexto", "Texto por defecto.");

            // Recogemos la preferencia de color, y asignamos el color en
            // funci�n de la preferencia. Si no se ha definido la propiedad por
            // defecto aplicamos la transpariencia.
            String prefColor = sharedPreferences.getString("lpFondo", "transparent");


                if (prefColor.equals("red"))
                    color = Color.RED;
                else if (prefColor.equals("gray"))
                    color = Color.GRAY;
                else if (prefColor.equals("blue"))
                    color = Color.BLUE;
                else
                    color = Color.TRANSPARENT;




            // Recogemos la preferencia de tama�o, y asignamos el tama�o en
            // funci�n de la preferencia. Si no se ha definido la propiedad por
            // defecto utilizaremos el tama�o normal.
            String prefTextSize = sharedPreferences.getString("lpTexto", "normal");
            if (prefTextSize.equals("medium"))
                textSize = 18;
            else if (prefTextSize.equals("big"))
                textSize = 22;
            else
                textSize = 14;
        } else {
            // Si no se desean aplicar las preferencias damos valores por
            // defecto.
            ola = "Texto por defecto.";
            color = Color.TRANSPARENT;
            textSize = 18;
        }

        prueba.setText(ola);
        prueba.setBackgroundColor(color);
        prueba.setTextSize(textSize);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, MyPreferencesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

            Intent cerrar = new Intent(this, MainActivity.class);
            startActivity(cerrar);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
