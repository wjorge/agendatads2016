package com.example.n51.agendaTADS;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tela_inicial extends AppCompatActivity {
    private CalendarView calendario;
//    private Button bt_compromisso;
    private Button bt_agendar;
    private String data_compromisso;
    private  Calendar user;
    private Button bt_filtrar;
    private Button bt_tipo;

    private TextView titulo;
    private Button bt_voltar;
    private ListView lista_de_compromissos;
    private  Cursor cursor;
    private ImageButton sair_aplicativo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        sair_aplicativo = (ImageButton) findViewById(R.id.sair_aplicativo);

        //-------------------------IMPORTAÇÃO OUTRA CLASSE
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
                Intent intent = new Intent(Tela_inicial.this, Tela_de_retificacao.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                onPause();
            }
        });

        //-------------------------FIM

        calendario = (CalendarView) findViewById(R.id.calendario);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int ano, int mes, int dia) {
                Integer.toString(dia);
                int meses = mes + 1;
                Integer.toString(meses);
                Integer.toString(ano);

                user = new GregorianCalendar(ano, mes, (dia + 1));//pega a  data desejada

                data_compromisso = dia + "/" + meses + "/" + ano;
                Toast.makeText(getApplicationContext(), data_compromisso, Toast.LENGTH_SHORT).show();
            }
        });


        bt_agendar=(Button)findViewById(R.id.bt_agendar);
        bt_agendar.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Date data = null;


                                              //chamando a classe caledar para ver se a data ja se passou
                                              Calendar now = new GregorianCalendar();
                                              if (data_compromisso == null) { //se não selecionou uma data
                                                  Toast.makeText(getApplicationContext(), "Selecione uma data", Toast.LENGTH_SHORT).show();
                                              } else if (user.before(now)) {// se data selecionada ja se passou
                                                  Toast.makeText(getApplicationContext(), "Essa data ja se passou selecione uma atual ou posterior !!", Toast.LENGTH_SHORT).show();

                                              } else {// se a data for atual ou posterior chama a  outra tela
                                                  Intent intent = new Intent(Tela_inicial.this, Tela_de_cadastro.class);
                                                  intent.putExtra("data_compromisso", data_compromisso);
                                                  startActivity(intent);
                                                  onPause();
                                              }
                                          }
                                      }

        );
//            bt_compromisso=(Button) findViewById(R.id.bt_compromisso);
//
//            bt_compromisso.setOnClickListener(new View.OnClickListener()
//
//            {
//                @Override
//                public void onClick (View v){
//                Intent intent = new Intent(Tela_inicial.this, Lista_Compromissos.class);
//                intent.putExtra("data_compromisso",data_compromisso);
//                startActivity(intent);
//                onPause();
//            }
//
//            });

        sair_aplicativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        bt_tipo=(Button)findViewById(R.id.bt_tipo);
//        bt_tipo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Tela_inicial.this, Eventos.class);
//                startActivity(intent);
//                onPause();
//            }
//
//        });

        bt_filtrar=(Button)findViewById(R.id.bt_filtrar);
        bt_filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Tela_inicial.this, Lista_Compromissos.class);
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





}

