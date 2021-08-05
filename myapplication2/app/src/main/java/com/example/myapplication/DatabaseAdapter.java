package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
    private static  Helper dphelper;
    private Context context;


    public  DatabaseAdapter(Context context)
    {
        this.context = context;
        dphelper = new Helper(context);
    }


    public long insertUser(User user){

        SQLiteDatabase dp = dphelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
       // cv.put(Helper.UID, user.getId());
        cv.put(Helper.PHONE, user.getPhone());
        cv.put(Helper.MESSAGE, user.getMessage());

        long id = dp.insert(Helper.TABLE_NAME, null, cv);

        return  id;
    }


    public User getAllUsers(){

        //User users[] = null;
        SQLiteDatabase dp = dphelper.getReadableDatabase();
        //  int i = 0;
        Cursor c ;

        User user=null;
        String columns[] = {Helper.UID, Helper.PHONE, Helper.MESSAGE};
        c = dp.query(Helper.TABLE_NAME, columns, null,
                null, null, null, null);

        //users = new User[c.getCount()];

        if(c.moveToLast())
        {
            user = new User(c.getString(1), c.getString(2));

        }


        return user;

    }
    private static class Helper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "myDatabase.db";
        public static final String TABLE_NAME = "user_data";
        public static final String UID = "_id";
        public static final String PHONE = "phone";
        public static final String MESSAGE = "message";
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + PHONE + " VARCHAR(50) , " +
                MESSAGE + " VARCHAR(200))";

        public Helper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }



    }

}



