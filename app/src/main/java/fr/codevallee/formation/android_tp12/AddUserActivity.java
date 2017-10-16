package fr.codevallee.formation.android_tp12;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button addUserButton = (Button) findViewById(R.id.add_user_add);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstnameField = (EditText) findViewById(R.id.firstname_field_add);
                String firstname = firstnameField.getText().toString();
                EditText lastnameField = (EditText) findViewById(R.id.lastname_field_add);
                String lastname = lastnameField.getText().toString();
                EditText ageField = (EditText) findViewById(R.id.age_field_add);
                String ageValue = ageField.getText().toString();
                Integer age = Integer.parseInt(ageValue.isEmpty() ? "0" : ageValue);
                EditText workField = (EditText) findViewById(R.id.work_field_add);
                String work = workField.getText().toString();

                if (!firstname.isEmpty() && !lastname.isEmpty()) {
                    User user = new User(null, firstname, lastname, age, work);
                    Log.d("Info", user.serialize());
                    Context context = AddUserActivity.this;
                    UserDataSource dataSource = new UserDataSource(context);
                    UserDAO userDAO = new UserDAO(dataSource);

                    user = userDAO.createUser(user);
                    Log.d("Info", user.serialize());

                    Intent backIntent = new Intent(context, MainActivity.class);
                    startActivity(backIntent);
                }
            }
        });
    }
}
