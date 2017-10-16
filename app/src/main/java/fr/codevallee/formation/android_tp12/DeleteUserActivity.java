package fr.codevallee.formation.android_tp12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        Intent infos = getIntent();
        final Integer userId = infos.getIntExtra("userId", 0);

        final Intent back = new Intent(DeleteUserActivity.this, MainActivity.class);
        if(userId == 0) {
            startActivity(back);
        }

        UserDataSource dataSource = new UserDataSource(DeleteUserActivity.this);
        final UserDAO userDAO = new UserDAO(dataSource);
        User user = new User(userId);
        user = userDAO.readUser(user);

        TextView confirmDeletionText = (TextView) findViewById(R.id.confirm_user_deletion);
        confirmDeletionText.setText(
                getString(R.string.confirm_delete_user) + " "
                        + user.getFirstname() + " "
                        + user.getLastname()
                        + "(âge : " + user.getAge() + ", " +
                        "métier : " + user.getWork() + ") ?");

        Button confirmDeletionButton = (Button) findViewById(R.id.confirm_user_deletion_button);
        confirmDeletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(userId);
                userDAO.deleteUser(user);

                startActivity(back);
            }
        });

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(back);
            }
        });
    }
}
