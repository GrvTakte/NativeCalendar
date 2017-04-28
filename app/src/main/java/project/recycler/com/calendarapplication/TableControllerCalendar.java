package project.recycler.com.calendarapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 25/04/17.
 */

public class TableControllerCalendar extends CalendarDatabase {

    public TableControllerCalendar(Context context){
        super(context);
    }

    public boolean create(ObjectCalendar objectcalendar){
        ContentValues values = new ContentValues();
        values.put("Date",objectcalendar.date);
        values.put("EventName",objectcalendar.eventname);
        values.put("EventDescription", objectcalendar.eventdescription);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("calendar_events",null,values)>0;
        db.close();
        return createSuccessful;
    }

    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM  calendar_events";
        int eventCount = db.rawQuery(sql,null).getCount();
        return eventCount;
    }

    public List<ObjectCalendar> read(){

        List<ObjectCalendar> eventList = new ArrayList<ObjectCalendar>();
        String sql = "SELECT * FROM calendar_events ORDER BY Id desc";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
                String EventName = cursor.getString(cursor.getColumnIndex("EventName"));
                String EventDate = cursor.getString(cursor.getColumnIndex("Date"));
                String EventDescription = cursor.getString(cursor.getColumnIndex("EventDescription"));

                ObjectCalendar objectCalendar = new ObjectCalendar();
                objectCalendar.id = id;
                objectCalendar.date = EventDate;
                objectCalendar.eventname = EventName;
                objectCalendar.eventdescription = EventDescription;

                eventList.add(objectCalendar);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;

    }

}
