package com.appnamao.tafeb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appnamao.tafeb.Database.DatabaseHelper;
//import com.appnamao.tafeb.Model.StractEmployee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    String mensao = "";
    int idade = 0;
    int linhaEnsino = 0;
    int sexo = 0;
    String compareMensao = "Muito Bom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase SqLiteDatabase = databaseHelper.getReadableDatabase();

        Spinner spIdade = findViewById(R.id.sp_idade);
        Spinner spMensao = findViewById(R.id.sp_mensao);
        Spinner spSexo = findViewById(R.id.sp_sexo);
        Spinner spLe = findViewById(R.id.sp_le);

        Log.i("-- Idade -- ", "" + idade);

        Button btnBuscar = findViewById(R.id.bt_buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int idadeCompare = spIdade.getSelectedItemPosition();
                if( idadeCompare == 0 ){
                    Toast.makeText(MainActivity.this,"Selecione a idade",Toast.LENGTH_SHORT).show();
                    Log.i("-- IDADE -- ", "Selecione a idade.");
                    return;
                } else {
                    idade = Integer.parseInt(spIdade.getSelectedItem().toString());
                    Log.i("-- Idade 54-- ", "" + idade);
                }

                mensao = spMensao.getSelectedItem().toString();
                sexo = spSexo.getSelectedItemPosition();
                linhaEnsino = spLe.getSelectedItemPosition();

                if( mensao.equals(compareMensao) ){
                    mensao = "Muito_bom";
                }

                if ( mensao.equals("Selecione a Mensão") || sexo == 0 || linhaEnsino == 0) {
                    Toast.makeText(MainActivity.this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show();
                    Log.i("-- AA -- ", "Preencha todos os campos.");
                    return;

                }

                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                intent.putExtra("mensao", mensao);
                intent.putExtra("idade", idade);
                intent.putExtra("sexo", sexo);
                intent.putExtra("linhaEnsino", linhaEnsino);
                startActivityForResult(intent,
                        1);
            }
        });

        ArrayList<String> idadeList = new ArrayList<>();
        idadeList.add(0, "Selecione a idade");
        idadeList.add("18");  idadeList.add("19");  idadeList.add("20");  idadeList.add("21");
        idadeList.add("22");  idadeList.add("23");  idadeList.add("24");  idadeList.add("25");
        idadeList.add("26");  idadeList.add("27");  idadeList.add("28");  idadeList.add("29");
        idadeList.add("30");  idadeList.add("31");  idadeList.add("32");  idadeList.add("33");
        idadeList.add("34");  idadeList.add("35");  idadeList.add("36");  idadeList.add("37");
        idadeList.add("38");  idadeList.add("39");  idadeList.add("40");  idadeList.add("41");
        idadeList.add("42");  idadeList.add("43");  idadeList.add("44");  idadeList.add("45");
        idadeList.add("46");  idadeList.add("47");  idadeList.add("48");  idadeList.add("49");
        idadeList.add("50");  idadeList.add("51");  idadeList.add("52");  idadeList.add("53");
        idadeList.add("54");  idadeList.add("55");  idadeList.add("56");  idadeList.add("57");
        idadeList.add("58");  idadeList.add("59");  idadeList.add("60");
        ArrayAdapter<String> adapterIdade = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_list, idadeList);
        adapterIdade.setDropDownViewResource(android.R.layout.select_dialog_item);
        spIdade.setAdapter(adapterIdade);
//        spIdade.setSelection(0, true);

        ArrayList<String> mensaoList = new ArrayList<>();
        mensaoList.add("Selecione a Mensão");
        mensaoList.add("Excelente");
        mensaoList.add("Muito Bom");
        mensaoList.add("Bom");
        mensaoList.add("Regular");
        mensaoList.add("Insuficiente");
        ArrayAdapter<String> adapterMensao = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_list, mensaoList);
        adapterMensao.setDropDownViewResource(android.R.layout.select_dialog_item);
        spMensao.setAdapter(adapterMensao);

        ArrayList<String> sexoList = new ArrayList<>();
        sexoList.add("Selecione o gênero");
        sexoList.add("M");
        sexoList.add("F");
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_list, sexoList);
        adapterSexo.setDropDownViewResource(android.R.layout.select_dialog_item);
        spSexo.setAdapter(adapterSexo);

        ArrayList<String> leList = new ArrayList<>();
        leList.add("Selecione a linha de ensino");
        leList.add("LEMB");
        leList.add("LEMS");
        ArrayAdapter<String> adapterLe = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_list, leList);
        adapterLe.setDropDownViewResource(android.R.layout.select_dialog_item);
        spLe.setAdapter(adapterLe);

    }

}