package com.appnamao.tafeb;

import android.content.Intent;

import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.appnamao.tafeb.Database.DatabaseHelper;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;
    private TextView mensaoTv;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdataactivity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView = findViewById(R.id.list_item_id);
        mensaoTv = findViewById(R.id.tvMensao);
        databaseHelper = new DatabaseHelper(this);

        Intent it = getIntent();
        String mensao = it.getStringExtra("mensao");
        int idade = it.getIntExtra("idade", 0);
        int sexo = it.getIntExtra("sexo", 0);
        int linhaEnsino = it.getIntExtra("linhaEnsino", 0);

        // Chama carrega dados
        loadData( mensao, idade, sexo, linhaEnsino );

        Button btnBuscar = findViewById(R.id.bKbutton);

        btnBuscar.setOnClickListener(new View.OnClickListener()    {
            public void onClick (View v){
                finish();
            }
        });

        if(mensao.equals("Muito_bom")){
            mensao = "Muito Bom";
        }
        mensaoTv.setText(mensao);
        mensaoTv.setPaintFlags(mensaoTv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void loadData( String mensao, int idade, int sexo, int linhaEnsino ) {
        ArrayList<String> listData = new ArrayList<>();

        Cursor cursor = databaseHelper.showAllData( mensao, idade, sexo, linhaEnsino );

        while( cursor.moveToNext()){
            listData.add(cursor.getString(0) + ":     " + cursor.getString(1));
//            int rModalidade = cursor.getColumnIndex("Modalidade");
//            int rMensao = cursor.getColumnIndex(mensao);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textviewid, listData);
        listView.setAdapter(adapter);
    }
}
