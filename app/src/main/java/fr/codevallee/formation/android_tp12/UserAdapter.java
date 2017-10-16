package fr.codevallee.formation.android_tp12;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tgoudouneix on 16/10/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user,parent, false);
        }

        UserViewHolder viewHolder = (UserViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new UserViewHolder();
            viewHolder.firstname = (TextView) convertView.findViewById(R.id.firstname);
            viewHolder.lastname = (TextView) convertView.findViewById(R.id.lastname);
            viewHolder.age = (TextView) convertView.findViewById(R.id.age);
            viewHolder.work = (TextView) convertView.findViewById(R.id.work);
            viewHolder.deleteButton = (Button) convertView.findViewById(R.id.delete_user);
            convertView.setTag(viewHolder);
        }

        final User user = getItem(position);

        viewHolder.firstname.setText(user.getFirstname());
        viewHolder.lastname.setText(user.getLastname());
        viewHolder.age.setText(user.getAge() + " ans");
        viewHolder.work.setText("(" + user.getWork() + ")");
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(parent.getContext(), DeleteUserActivity.class);
                deleteIntent.putExtra("userId", (int) user.getId());
                parent.getContext().startActivity(deleteIntent);
            }
        });

        return convertView;
    }

    private class UserViewHolder {
        public TextView firstname;
        public TextView lastname;
        public TextView age;
        public TextView work;
        public Button deleteButton;
    }
}
