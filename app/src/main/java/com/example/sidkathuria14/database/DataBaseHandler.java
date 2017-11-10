package com.example.sidkathuria14.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sidkathuria14.database.model.Contact;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by sidkathuria14 on 10/11/17.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME= "contactsManagar";
    public static final String DB_TABLE = "contacts";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name" ;
    public static final String KEY_NUMBER = "phone_number";

    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DB_TABLE + "( " + KEY_ID +  " INTEGER PRIMARY KEY,"
        + KEY_NAME + " TEXT," + KEY_NUMBER + " TEXT"
        + " ) ";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact){
SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_NUMBER,contact.getNumber());
        db.insert(DB_TABLE,null,values);
        db.close();

    }
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE, new String[]{
                KEY_ID, KEY_NAME, KEY_NUMBER
        }, KEY_ID + " =? ", new String[]{
                String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
return contact;
    }

    public List<Contact> getAllContacts(){
List<Contact> contactList = new ArrayList<Contact>();
    String selectQuery = "SELECT * FROM " + DB_TABLE;
    SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getString(2));

            } while(cursor.moveToNext());
        }
        return contactList;

    }
    public int getContactsCount(){

        String countQuery= "SELECT  * FROM " + DB_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateContact(Contact contact){
SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_NUMBER,contact.getNumber());
        return  db.update(DB_TABLE,values,KEY_ID + " =? ",new String[]{
                String.valueOf(contact.getId())
        });
    }

    public void deleteContact(Contact contact){
SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,KEY_ID + " =? ",new String[]{
String.valueOf(contact.getId())
        });

        db.close();
    }

}
