package com.example.sidkathuria14.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sidkathuria14.database.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
public static final String TAG= "DB";
    EditText etName,etNumber;
    String name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
       final DataBaseHandler db = new DataBaseHandler(this);

//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//        db.addContact(new Contact("Karthik", "9533333333"));



etName = (EditText)findViewById(R.id.etName);
        etNumber = (EditText)findViewById(R.id.etNumber);
        name = etName.getText().toString();
        number = etNumber.getText().toString();

        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addContact(new Contact(name,number));
                etName.setText("");
                etNumber.setText("");
                Toast.makeText(MainActivity.this, "Added to database succesfully!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: updated! size =  " + db.getContactsCount() + " ");
            }
        });

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


