package fr.codevallee.formation.android_tp12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView usersView = (ListView) findViewById(R.id.user_list);

        UserDataSource dataSource = new UserDataSource(this);
        UserDAO userDAO = new UserDAO(dataSource);
        ArrayList<User> users = (ArrayList<User>) userDAO.readAllUsers();
        String[] usersList = new String[users.size()];

        for(int i = 0; i < users.size(); i++) {
            usersList[i] = users.get(i).serialize();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, usersList);
        usersView.setAdapter(adapter);

        Button addUserButton = (Button) findViewById(R.id.add_user_main);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddUserForm = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intentAddUserForm);
            }
        });
    }
}
