package fr.codevallee.formation.android_tp12;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgoudouneix on 16/10/2017.
 */

public class UserDAO {
    private UserDataSource dataSource;

    public UserDAO(UserDataSource userDataSource) {
        this.dataSource = userDataSource;
    }

    public synchronized User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(dataSource.getHelper().COL_FIRSTNAME, user.getFirstname());
        values.put(dataSource.getHelper().COL_LASTNAME, user.getLastname());
        values.put(dataSource.getHelper().COL_AGE, user.getAge());
        values.put(dataSource.getHelper().COL_WORK, user.getWork());

        int id = (int) dataSource.getDb().insert(dataSource.getHelper().TABLE_NAME, null, values);

        user.setId(id);

        return user;
    }

    public User readUser(User user) {
        String[] allColumns = new String[]{dataSource.getHelper().COL_ID, dataSource.getHelper().COL_FIRSTNAME, dataSource.getHelper().COL_LASTNAME, dataSource.getHelper().COL_AGE, dataSource.getHelper().COL_WORK};

        String clause = dataSource.getHelper().COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        Cursor cursor = dataSource.getDb().query(dataSource.getHelper().TABLE_NAME, allColumns, "ID = ?", clauseArgs, null, null, null);

        cursor.moveToFirst();

        user.setFirstname(cursor.getString(1));
        user.setLastname(cursor.getString(2));
        user.setAge(cursor.getInt(3));
        user.setWork(cursor.getString(4));

        cursor.close();
        return user;
    }

    public synchronized User updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(dataSource.getHelper().COL_FIRSTNAME, user.getFirstname());
        values.put(dataSource.getHelper().COL_LASTNAME, user.getLastname());
        values.put(dataSource.getHelper().COL_AGE, user.getAge());
        values.put(dataSource.getHelper().COL_WORK, user.getWork());

        String clause = dataSource.getHelper().COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        dataSource.getDb().update(dataSource.getHelper().TABLE_NAME, values, clause, clauseArgs);

        return user;
    }

    public synchronized void deleteUser(User user) {
        String clause = dataSource.getHelper().COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        dataSource.getDb().delete(dataSource.getHelper().TABLE_NAME, clause, clauseArgs);
    }

    public List<User> readAllUsers() {
        String[] allColumns = new String[]{dataSource.getHelper().COL_ID, dataSource.getHelper().COL_FIRSTNAME, dataSource.getHelper().COL_LASTNAME, dataSource.getHelper().COL_AGE, dataSource.getHelper().COL_WORK};

        Cursor cursor = dataSource.getDb().query(dataSource.getHelper().TABLE_NAME, allColumns, null, null, null, null, null);

        List<User> users = new ArrayList<User>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4)));
            cursor.moveToNext();
        }

        cursor.close();
        return users;
    }
}
