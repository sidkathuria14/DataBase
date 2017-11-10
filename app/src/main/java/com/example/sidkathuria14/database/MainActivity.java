package com.example.sidkathuria14.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sidkathuria14.database.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
public static final String TAG= "DB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        DataBaseHandler db = new DataBaseHandler(this);

        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
        db.addContact(new Contact("Karthik", "9533333333"));


        Log.d(TAG, "onCreate: " + db.getContactsCount() + " ");
        List<Contact> myList = db.getAllContacts();

        for(Contact c : myList){
            Log.d(TAG, "onCreate: ");

            String log = "Id: "+c.getId()+" ,Name: " + c.getName() + " ,Phone: " + c.getNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}


