package com.example.n51.agendaTADS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco_de_dados extends SQLiteOpenHelper {
        public static final String nome_bd="Agenda";
        public static final int versao_bd=1;
        public  static  final String Tabela="compromissos";
        public static final String Id="_id";
        public static final String data_inicio="data_inicio";
        public static  final String hora_inicio="hora_inicio";
        public static  final String hora_fim="hora_fim";
        public static  final String local="local";
        public static  final String descricao="descricao";
         public static  final String tipo_de_evento="tipo_de_evento";
        public static  final String participantes="participantes";
        public static  final String ocorrencias="ocorrencias";
      public static  final String qntd_ocorrencias="qntd_ocorrencias";
       public static  final String temp="temp";
         public static  final String temp2="temp2";

        public Banco_de_dados(Context context) {
            super(context, nome_bd, null, versao_bd);
        }
    @Override
        public void onCreate(SQLiteDatabase db) {

        String criando_bd="CREATE TABLE compromissos( "+
                            Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            "data_inicio TEXT,"+
                            hora_inicio+" TEXT,"+
                            hora_fim+" TEXT,"+
                            local+" TEXT,"+
                            descricao+" TEXT,"+
                            tipo_de_evento+" TEXT,"+
                           " participantes TEXT,"+
                            ocorrencias +" INT,"+
                            "qntd_ocorrencias TEXT,"+
                             "temp TEXT,"+
                               "temp2 TEXT);";
            db.execSQL(criando_bd);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String exclui_bd="DROP TABLE IF EXISTS"+Tabela;
            db.execSQL(exclui_bd);
            onCreate(db);       }
}





