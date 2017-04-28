package project.recycler.com.calendarapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView dateDisplay;
    EditText editTextEventName;
    EditText editTextaeventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countEvents();

        Button add = (Button) findViewById(R.id.add_event_button);
        Button cancel = (Button) findViewById(R.id.cancel_event_button);



        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateDisplay = (TextView) findViewById(R.id.date_display);
        dateDisplay.setText("Date: ");
        editTextEventName = (EditText) findViewById(R.id.event_name);
        editTextaeventDescription = (EditText) findViewById(R.id.event_description);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                    i1++;
                dateDisplay.setText("Date: " +i+ "/" +i1+ "/" + i2);
                //Toast.makeText(getApplicationContext(),"Selected Date: \n"+"Day= "+i2+ "Month: " +i1+ "Year: " +i,Toast.LENGTH_LONG).show();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DisplayEventActivity.class);
                startActivity(myIntent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event_date = dateDisplay.getText().toString();
                String event_name = editTextEventName.getText().toString();
                String event_description = editTextaeventDescription.getText().toString();

                ObjectCalendar objectcalendar = new ObjectCalendar();
                objectcalendar.date = event_date;
                objectcalendar.eventname = event_name;
                objectcalendar.eventdescription = event_description;

                boolean createSuccessful = new TableControllerCalendar(getApplicationContext()).create(objectcalendar);

                Toast.makeText(getApplicationContext(),event_date+event_name+event_description,Toast.LENGTH_SHORT).show();

                if(createSuccessful){
                    Toast.makeText(getApplicationContext(), "Student information was saved.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Unable to save student information.", Toast.LENGTH_SHORT).show();
                }
                    countEvents();
            }
        });

    }


    public void countEvents(){
        int eventCount = new TableControllerCalendar(getApplicationContext()).count();
        TextView textViewEventCount = (TextView) findViewById(R.id.event_number);
        textViewEventCount.setText(eventCount+" Events added");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add:
               Intent i = new Intent(MainActivity.this,DisplayEventActivity.class);
                startActivity(i);


        }
            return(super.onOptionsItemSelected(item));
    }


}
