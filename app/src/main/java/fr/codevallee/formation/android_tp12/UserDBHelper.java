package fr.codevallee.formation.android_tp12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tgoudouneix on 16/10/2017.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "User";

    public static final String COL_ID           = "id";
    public static final String COL_FIRSTNAME    = "firstname";
    public static final String COL_LASTNAME     = "lastname";
    public static final String COL_AGE          = "age";
    public static final String COL_WORK         = "work";

    public UserDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UserDBHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public static String getQueryCreate() {
        return "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ID + " Integer PRIMARY KEY AUTOINCREMENT, "
            + COL_FIRSTNAME + " Text NOT NULL, "
            + COL_LASTNAME + " Text NOT NULL, "
            + COL_AGE + " Integer NULL DEFAULT 0, "
            + COL_WORK + " Text NULL"
            + ");";
    }

    public static String getQueryDrop() {
        return "DROP TABLE IF EXISTS User;";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getQueryCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getQueryDrop());
        db.execSQL(getQueryCreate());
    }
}
