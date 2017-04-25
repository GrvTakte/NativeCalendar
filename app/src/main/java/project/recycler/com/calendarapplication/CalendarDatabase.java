package project.recycler.com.calendarapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gaurav on 24/04/17.
 */

public class CalendarDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calendar";

    public CalendarDatabase(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE calendar_events(Id INTEGER PRIMARY KEY AUTOINCREMENT,Date date,EventName text,EventDescription text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion){
        String sql = "DROP TABLE IF EXISTS calendar_events";
        db.execSQL(sql);
        onCreate(db);
    }


    public void createRecord(){

    }
}
