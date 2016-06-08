package com.example.n51.agendaTADS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * criado por WJorge 28/05/2016.
 */
public class Banco_de_dados_aux {

    private Banco_de_dados manterbd;
    private Bundle bundle;
    private Context ctx;
    private Calendar user;

    public Banco_de_dados_aux(Context context) {
        manterbd = new Banco_de_dados(context);
        ctx = context;
    }



    public String insere_dados_tabela(String data_inicio, String hora_inicio, String hora_fim, String local, String descricao, String tipo_de_evento, String participantes, String ocorrencias, String qntd_ocorrencias, String temp, String temp2) {

        SQLiteDatabase db = manterbd.getWritableDatabase();
        ContentValues inserindo = new ContentValues();
        inserindo.put(Banco_de_dados.data_inicio, data_inicio);
        inserindo.put(Banco_de_dados.hora_inicio, hora_inicio);
        inserindo.put(Banco_de_dados.hora_fim, hora_fim);
        inserindo.put(Banco_de_dados.local, local);
        inserindo.put(Banco_de_dados.descricao, descricao);
        inserindo.put(Banco_de_dados.tipo_de_evento, tipo_de_evento);
        inserindo.put(Banco_de_dados.participantes, participantes);
        inserindo.put(Banco_de_dados.ocorrencias, ocorrencias);
        inserindo.put(Banco_de_dados.qntd_ocorrencias, qntd_ocorrencias);
        inserindo.put(Banco_de_dados.temp, temp);
        inserindo.put(Banco_de_dados.temp2, temp2);


        Long resultado;
        resultado = db.insert(Banco_de_dados.Tabela, null, inserindo);
        db.close();
        if (resultado == -1)
            return "Erro ao Registrar";
        else
            return "Registrado com sucesso";
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.data_inicio, manterbd.hora_inicio, manterbd.hora_fim, manterbd.local, manterbd.descricao, manterbd.tipo_de_evento, manterbd.ocorrencias, manterbd.qntd_ocorrencias, manterbd.participantes, manterbd.temp, manterbd.temp2,};
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(manterbd.Tabela, campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadoById(int id) {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.data_inicio, manterbd.hora_inicio, manterbd.hora_fim, manterbd.local, manterbd.descricao, manterbd.tipo_de_evento};
        String where = Banco_de_dados.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(Banco_de_dados.Tabela, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadoById2(int id) {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.participantes, manterbd.ocorrencias, manterbd.qntd_ocorrencias, manterbd.temp, manterbd.temp2};
        String where = Banco_de_dados.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(Banco_de_dados.Tabela, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public void alteraRegistro(int id, String data_inicio, String hora_inicio, String hora_fim, String local, String descricao, String tipo_de_evento, String participantes, String ocorrencias, String repeticoes, String temp, String temp2) {
        ContentValues valores;
        String where;
        SQLiteDatabase db = manterbd.getWritableDatabase();
        where = Banco_de_dados.Id + "=" + id;
        valores = new ContentValues();
        valores.put(Banco_de_dados.data_inicio, data_inicio);
        valores.put(Banco_de_dados.hora_inicio, hora_inicio);
        valores.put(Banco_de_dados.hora_fim, hora_fim);
        valores.put(Banco_de_dados.local, local);
        valores.put(Banco_de_dados.descricao, descricao);
        valores.put(Banco_de_dados.tipo_de_evento, tipo_de_evento);
        valores.put(Banco_de_dados.participantes, participantes);
        valores.put(Banco_de_dados.ocorrencias, ocorrencias);
        valores.put(Banco_de_dados.qntd_ocorrencias, repeticoes);
        valores.put(Banco_de_dados.temp, temp);
        valores.put(Banco_de_dados.temp2, temp2);

        db.update(Banco_de_dados.Tabela, valores, where, null);
        db.close();
    }

    public void deletaRegistro(int id) {
        String where = Banco_de_dados.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        db.delete(Banco_de_dados.Tabela, where, null);
        db.close();
    }


    public void expurgar_compromissos(String data_inicio, String data_fim) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data_ini = new java.sql.Date(formatter.parse(data_inicio).getTime());
        Date data_f = new java.sql.Date(formatter.parse(data_fim).getTime());
    }


}













