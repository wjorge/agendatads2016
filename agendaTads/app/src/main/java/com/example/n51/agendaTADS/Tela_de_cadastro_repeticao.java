package com.example.n51.agendaTADS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Tela_de_cadastro_repeticao extends AppCompatActivity {
    Mascaras_de_campos mask=new Mascaras_de_campos() ;
    private Spinner spinner2;
    private Spinner spinner3;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private EditText editText;
    private EditText editText2;
    private Button bt_cadastrar;
    private Button bt_voltar;
    private EditText edit_participantes;
    private ArrayAdapter<String> ocorrencias;
    private ArrayAdapter<String> repeticao;
    private String data_inicio;
    private String hora_ini;
    private String hora_fim;
    private String  local;
     private String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_de_cadastro_repeticao);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        edit_participantes=(EditText)findViewById(R.id.edit_participantes);
        bt_cadastrar = (Button) findViewById(R.id.bt_cadastrar);
        bt_voltar = (Button) findViewById(R.id.bt_voltar);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        editText2.addTextChangedListener(mask.insert("##/##/####",editText2));


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ocorrencias = new ArrayAdapter<String>(Tela_de_cadastro_repeticao.this, android.R.layout.simple_spinner_item);
        ocorrencias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(ocorrencias);
        ocorrencias.add("diarimente");
        ocorrencias.add("semanalmente");
        ocorrencias.add("mensalmente");
        ocorrencias.add("anualmente");


        spinner3 = (Spinner) findViewById(R.id.spinner3);
        repeticao = new ArrayAdapter<String>(Tela_de_cadastro_repeticao.this, android.R.layout.simple_spinner_item);
        repeticao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(repeticao);
        repeticao.add("1 dia");
        repeticao.add("1 semana");
        repeticao.add("1 mês");
        repeticao.add("1 ano");


        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banco_de_dados_aux banco_de_dadosAux =new Banco_de_dados_aux(getBaseContext());
                Bundle bundle=getIntent().getExtras();
                String data_inicio=bundle.getString("data_inicio");
                String hora_ini=bundle.getString("hora_ini");
                String hora_fim=bundle.getString("hora_fim");
                String local=bundle.getString("local");
                String descricao=bundle.getString("descricao");
                String tipo_de_evento=bundle.getString("tipo_de_evento");


                String qnt_participantes = edit_participantes.getText().toString();
                String num_ocorrencias = editText.getText().toString();
                String sempre_repetir="sempre repetir";
                String data_final = editText2.getText().toString();
                String ocorrencias=spinner2.getSelectedItem().toString();
                String repeticao = spinner3.getSelectedItem().toString();
                String ocorrencia_selecionada=ocorrencias;
                String repeticoes_selecionada=repeticao;




                String resultado;
                String radio;
                if (qnt_participantes.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Existe algum campo vazio!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (radioButton1.isChecked()) {
                        if (num_ocorrencias.trim().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Preencha o número de ocorrências !!", Toast.LENGTH_SHORT).show();
                        } else {
                                radio="1";

                            resultado = banco_de_dadosAux.insere_dados_tabela(data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,num_ocorrencias);
                            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Tela_de_cadastro_repeticao.this, Tela_inicial.class);
                            startActivity(intent);
                            onPause();



                        }

                    } else if (radioButton2.isChecked()) {
                        radio="2";
                        resultado = banco_de_dadosAux.insere_dados_tabela(data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,sempre_repetir);

                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tela_de_cadastro_repeticao.this, Tela_inicial.class);
                        startActivity(intent);
                        onPause();

                    } else if (radioButton3.isChecked()) {
                        if (data_final.trim().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Preencha com a data final !!", Toast.LENGTH_SHORT).show();

                        } else {


                            radio="3";
                            resultado = banco_de_dadosAux.insere_dados_tabela(data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,data_final);
                            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Tela_de_cadastro_repeticao.this, Tela_inicial.class);
                            startActivity(intent);
                            onPause();


                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Marque alguma opção acima !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

           bt_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(Tela_de_cadastro_repeticao.this, Tela_inicial.class);
                    startActivity(intent);
                    onPause();

                    //Toast.makeText(getApplicationContext(), "Em manutenção...", Toast.LENGTH_SHORT).show();


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
        setContentView(R.layout.tela_cadastro);
    }
}




























