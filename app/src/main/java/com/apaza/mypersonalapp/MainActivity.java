package com.apaza.mypersonalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.List;

//import static com.apaza.mypersonalapp.UserRepository.users;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText)findViewById(R.id.usurname);
        passwordInput = (EditText)findViewById(R.id.password);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }
    }


    public void ingresar(View view){

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();



        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Falta completar campos", Toast.LENGTH_SHORT).show();
            return;
        }
        //users.add(new User(username,password,"nuevo"));
        // Login logic
        //User user = UserRepository.login(username, password);


        UserRepository.create("julio", "tecsup", "Julio César");

        List<User> users = UserRepository.list();

        for (User user : users) {


            if (user.getUsusario().equalsIgnoreCase(username) && user.getPassword().equals(password)) {


            Toast.makeText(this, "Welcome " + user.getNombres(), Toast.LENGTH_SHORT).show();

            // Save to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean success = editor
                    .putString("username", user.getUsusario())
                    .putBoolean("islogged", true)
                    .commit();

        // Go to Dashboard
        goDashboard();
            } else{
                UserRepository.create(username,password,"Nuevo Usuario");
                Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();
            }
        }

       /* if (user == null) {
            Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show();
            return;
        }*/
    }

    private void goDashboard(){
        startActivity(new Intent(this, DrawerNavigationActivity.class));
        finish();
    }

}
