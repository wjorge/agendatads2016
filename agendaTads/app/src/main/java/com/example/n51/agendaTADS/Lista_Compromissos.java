package com.example.n51.agendaTADS;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Lista_Compromissos extends AppCompatActivity {
    private TextView titulo;
    private Button bt_agendar;
    private Button bt_voltar;
    private ListView lista_de_compromissos;
    private  Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_de_filtro);
        Toast.makeText(getApplicationContext(),"Filtre os compromissos por data para altera-los..", Toast.LENGTH_SHORT).show();

        Banco_de_dados_aux banco_de_dadosAux = new Banco_de_dados_aux(getBaseContext());
        cursor = banco_de_dadosAux.carregaDados();

      String[] nomeCampos = new String[] {Banco_de_dados.local, Banco_de_dados.data_inicio, Banco_de_dados.hora_inicio, Banco_de_dados.descricao, Banco_de_dados.tipo_de_evento, Banco_de_dados.ocorrencias, Banco_de_dados.qntd_ocorrencias, Banco_de_dados.participantes, Banco_de_dados.temp, Banco_de_dados.temp2};
        int[] idViews = new int[] {R.id.v_local,R.id.v_data_inicio,R.id.v_horario_inicial, R.id.v_descricao,R.id.spinner2,R.id.spinner3,R.id.participantes,R.id.radio};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,R.layout.tela_de_conteudo_filtro,cursor,nomeCampos,idViews, 0);
        lista_de_compromissos = (ListView)findViewById(R.id.lista_de_compromissos);
        lista_de_compromissos.setAdapter(adaptador);
        lista_de_compromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String codigo;

               cursor.moveToPosition(position);
               codigo = cursor.getString(cursor.getColumnIndexOrThrow(Banco_de_dados.Id));
               Intent intent = new Intent(Lista_Compromissos.this, Tela_de_retificacao.class);
               intent.putExtra("codigo", codigo);
               startActivity(intent);
               onPause();
           }
       });
        bt_voltar=(Button)findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lista_Compromissos.this, Tela_inicial.class);
                startActivity(intent);
                onPause();
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        finish();
        setContentView(R.layout.tela_inicial);
    }
}
