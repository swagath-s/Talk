package com.swagath.talk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Chatroom extends AppCompatActivity {

    FirebaseDatabase fdb;
    DatabaseReference dbref;

    ArrayList<String> displayItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        fdb = FirebaseDatabase.getInstance();
        dbref = fdb.getReference();

        lv = (ListView)findViewById(R.id.chatRoomListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayItems);
        lv.setAdapter(adapter);
        adapter.add("<pre-written ");


    }
}
