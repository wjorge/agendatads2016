package com.example.n51.agendaTADS;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Tela_de_cadastro extends AppCompatActivity {
    private TextView Data_de_compromisso;
    private TextView v_data_de_compromisso;
    private TextView horario_ini;
    private TextView v_horario_ini;
    private TextView horario_fim;
    private TextView v_horario_fim;
    private TextView local;
    private EditText edit_local;
    private TextView descricao;
    private EditText edit_descricao;
    private Button bt_proxima_tela;
    private  Button bt_voltar;
//    private EditText editDataTermino;
    private EditText edit_tipo_evento;
    private  Spinner spinner;
//    private Spinner spinner2;
    private ArrayAdapter<String> tipos_eventos;
    private ArrayAdapter<String> ocorrencias;
    private  int horas;
    private  int minutos;
    private int dia;
    private int mes;
    private int ano;
    private TextView textView3;

    ///////////////////////////////////////////////////////////////////////////////////
    Spinner sp;
    EditText nameTxt;
    Button addBtn, updateBtn,deleteBtn,clearBtn;
    ArrayList<String> names=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ///////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        Data_de_compromisso = (TextView) findViewById(R.id.Data_de_compromisso);
        v_data_de_compromisso = (TextView) findViewById(R.id.v_data_de_compromisso);
        horario_ini = (TextView) findViewById(R.id.horario_ini);
        v_horario_ini = (TextView) findViewById(R.id.v_horario_ini);
        horario_fim = (TextView) findViewById(R.id.horario_fim);
        v_horario_fim = (TextView) findViewById(R.id.v_horario_fim);
        local = (TextView) findViewById(R.id.local);
        edit_local = (EditText) findViewById(R.id.edit_local);
        descricao = (TextView) findViewById(R.id.descricao);
        edit_descricao = (EditText) findViewById(R.id.edit_descricao);
//        editDataTermino = (EditText) findViewById(R.id.editDataTermino);
        bt_proxima_tela = (Button) findViewById(R.id.bt_proxima_tela);
        bt_voltar = (Button) findViewById(R.id.bt_voltar);

        Bundle bundle=getIntent().getExtras();
        //Essa váriavel que armazena a data para mostrar na tela
        final String data_compromisso=bundle.getString("data_compromisso");
        v_data_de_compromisso.setText(data_compromisso);



        spinner=(Spinner)findViewById(R.id.sp);
        tipos_eventos=new ArrayAdapter<String>(Tela_de_cadastro.this,android.R.layout.simple_spinner_item);
        tipos_eventos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tipos_eventos);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        sp=(Spinner) findViewById(R.id.sp);
        nameTxt=(EditText)findViewById(R.id.nameTxt);
        addBtn=(Button)findViewById(R.id.addBtn);
        updateBtn= (Button)findViewById(R.id.updateBtn);
        deleteBtn = (Button)findViewById(R.id.deleteBtn);
        clearBtn = (Button)findViewById(R.id.clearBtn);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, names);
        tipos_eventos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        adapter.add("Escola");
        adapter.add("Trabalho");
        adapter.add("Lazer");
        adapter.add("Saúde");
        adapter.add("Familia");

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nameTxt.setText(names.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////


        v_horario_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timepicker = new TimePickerDialog(Tela_de_cadastro.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hora, int minuto) {
                        horas = hora;
                        minutos = minuto;
                        v_horario_ini.setText(horas + ":" + minutos);

                    }
                }, horas, minutos, true);
                timepicker.setTitle("Selecione o horário de inicio:");
                timepicker.show();
            }
        });


        v_horario_fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timepicker = new TimePickerDialog(Tela_de_cadastro.this,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hora, int minuto) {
                        horas=hora;
                        minutos=minuto;
                        v_horario_fim.setText(horas + ":" + minutos);

                    }
                },horas,minutos,true);
                timepicker.setTitle("Selecione o horário de fim:");
                timepicker.show();
            }
        });

//--------------------------------------------------------------------------------INICIO DA AÇÃO DO BOTÃO DE SALVAR
        bt_proxima_tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banco_de_dados_aux banco_de_dadosAux = new Banco_de_dados_aux(getBaseContext());
                final String data_inicio=v_data_de_compromisso.getText().toString();
                final String hora_ini=v_horario_ini.getText().toString();
                final String hora_fim=v_horario_fim.getText().toString();
                final String local=edit_local.getText().toString();
                final String descricao=edit_descricao.getText().toString();
                String tipo_evento_selecionado = spinner.getSelectedItem().toString();
                final String tipo_de_evento=tipo_evento_selecionado;


                if(data_inicio.trim().isEmpty() ||hora_ini.trim().isEmpty()||hora_fim.trim().isEmpty()||local.trim().isEmpty()||descricao.trim().isEmpty()||tipo_evento_selecionado.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Existe algum campo vazio ou vc não selecionou o tipo de evento desejado!!", Toast.LENGTH_SHORT).show();
                }else{

                    // String resultado=banco_de_dadosAux.insere_dados_tabela(data_inicio,hora_ini,hora_fim,local,descricao,tipo_de_evento);
                    Toast.makeText(getApplicationContext(),"Evento criado com Sucesso", Toast.LENGTH_SHORT).show();

                    //-------------------------------------------------------------------------------INICIO CODIGO NOVO
                    LayoutInflater layoutInflater = LayoutInflater.from(Tela_de_cadastro.this);
                    View promptView = layoutInflater.inflate(R.layout.tela_alerta_repeticao, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tela_de_cadastro.this);
                    alertDialogBuilder.setView(promptView);

                    alertDialogBuilder.setCancelable(false)

              //INÍCIO CRIAR REPETIÇÃO------------------------------------------------------------------------------------
                            .setPositiveButton("Criar repetição", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Intent intent = new Intent(Tela_de_cadastro.this, Tela_de_cadastro_repeticao.class);
                                    intent.putExtra("data_inicio", data_inicio);
                                    intent.putExtra("hora_ini", hora_ini);
                                    intent.putExtra("hora_fim", hora_fim);
                                    intent.putExtra("local", local);
                                    intent.putExtra("descricao", descricao);
                                    intent.putExtra("tipo_de_evento", tipo_de_evento);
                                    startActivity(intent);
                                    onPause();
                                }
                            })
              //FIM CRIAR REPETIÇÃO--------------------------------------------------------------------------------------

              //INÍCIO NÃO CRIAR REPETIÇÃO------------------------------------------------------------------------------------
                            .setNegativeButton("Não criar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Toast.makeText(getApplicationContext(), "Seu Compromisso SEM REPETIÇÃO foi criado com sucesso!", Toast.LENGTH_SHORT).show();

                                            Banco_de_dados_aux bdDados = new Banco_de_dados_aux(getBaseContext());
                                            String resultado;
                                            String qnt_participantes = "nulo";
                                            String ocorrencia_selecionada = "nulo";
                                            String repeticoes_selecionada = "nulo";
                                            String num_ocorrencias = "nulo";
                                            String radio = "nulo";
                                            resultado = bdDados.insere_dados_tabela(data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,num_ocorrencias);
                                            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Tela_de_cadastro.this, Tela_inicial.class);
                                            startActivity(intent);
                                            onPause();

                                            Intent it = new Intent(Tela_de_cadastro.this, Tela_inicial.class);
                                            startActivity(it);
                                        }
                                    });
               //FIM NÃO CRIAR REPETIÇÃO------------------------------------------------------------------------------------
                    // CRIANDO O DIALOGO DE ALERTA
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.show();
                    //-------------------------------------------------------------------------------FIM CODIGO NOVO
                }
            }
        });
//--------------------------------------------------------------------------------FIMDA AÇÃO DO BOTÃO DE SALVAR

//----------------------------------------------------------------------------INICIO DA AÇÃO DO BOTÃO DE VOLTAR
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_de_cadastro.this, Tela_inicial.class);
                startActivity(intent);
                onPause();
            }
        });
//----------------------------------------------------------------------------FIM DA AÇÃO DO BOTÃO DE VOLTAR

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void add()
    {
        String name=nameTxt.getText().toString();
        if(!name.isEmpty() && name.length()>0)
        {
            adapter.add(name);
            adapter.notifyDataSetChanged();
            nameTxt.setText("");
            Toast.makeText(getApplicationContext(), "adicionado" + name, Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "Não adicionou",Toast.LENGTH_SHORT).show();
        }
    }

    private void update()
    {
        String name=nameTxt.getText().toString();

        int pos=sp.getSelectedItemPosition();
        if(!name.isEmpty() && name.length()>0)
        {
            adapter.remove(names.get(pos));
            adapter.insert(name, pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "atualizado" + name, Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "não atualizado" + name, Toast.LENGTH_SHORT).show();
        }
    }

    private void delete()
    {
        int pos=sp.getSelectedItemPosition();
        if(pos > -1)
        {
            adapter.remove(names.get(pos));
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "deletado", Toast.LENGTH_SHORT).show();

            nameTxt.setText("");
        }else
        {
            Toast.makeText(getApplicationContext(), "nada para add", Toast.LENGTH_SHORT).show();
        }
    }

    private void clear()
    {
        adapter.clear();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public void repetir(){
//        Intent params = new Intent(this, Tela_de_cadastro_repeticao.class);
//        startActivity(params);
//    }

//    public void voltarMain(){
//        Intent it = new Intent(this, Tela_inicial.class);
//        startActivity(it);
//    }




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