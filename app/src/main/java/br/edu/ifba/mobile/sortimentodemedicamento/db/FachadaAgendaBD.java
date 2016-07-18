package br.edu.ifba.mobile.sortimentodemedicamento.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edigar on 15/07/2016.
 */
public class FachadaAgendaBD extends SQLiteOpenHelper {
    private static FachadaAgendaBD instancia = null;

    public static FachadaAgendaBD criarInstancia(Context context){
        if (instancia == null){
            instancia = new FachadaAgendaBD(context);
        }
        return instancia;
    }
    public static FachadaAgendaBD getInstancia(){
        return instancia;
    }

    private static String NOME_BANCO = "AgendaMedicamentos";
    private static int VERSAO_BANCO = 1;

    public FachadaAgendaBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_AGENDA =
            "CREATE TABLE AGENDA("+
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOMEMEDICAMENTOAGENDA TEXT, ESTOQUEAGENDA INTEGER, UMASEMANA INTEGER, TRESDIAS INTEGER, UMDIA INTEGER, DIA INTEGER, MES INTEGER, ANO INTEGER)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_AGENDA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }

    //CRUD

    public long inserir(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMEMEDICAMENTOAGENDA", agenda.getNomeMedicamentoAgenda());
        valores.put("ESTOQUEAGENDA", agenda.getEstoqueAgenda());
        valores.put("UMASEMANA", agenda.getUmaSemana());
        valores.put("TRESDIAS", agenda.getTresDias());
        valores.put("UMDIA", agenda.getUmDia());
        valores.put("DIA", agenda.getDia());
        valores.put("MES", agenda.getMes());
        valores.put("ANO", agenda.getAno());
        long codigo = db.insert("AGENDA", null, valores);
        return codigo;
    }

    public long atualizar(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMEMEDICAMENTOAGENDA", agenda.getNomeMedicamentoAgenda());
        valores.put("ESTOQUEAGENDA", agenda.getEstoqueAgenda());
        valores.put("UMASEMANA", agenda.getUmaSemana());
        valores.put("TRESDIAS", agenda.getTresDias());
        valores.put("UMDIA", agenda.getUmDia());
        valores.put("DIA", agenda.getDia());
        valores.put("MES", agenda.getMes());
        valores.put("ANO", agenda.getAno());
        long codigo = db.update("AGENDA", valores, "CODIGO = " + agenda.getCodigo(), null);
        return codigo;
    }

    public int remover(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("AGENDA","CODIGO = " + agenda.getCodigo(), null);
    }

    public List<Agenda> listarAgenda(){
        List<Agenda> agendas = new ArrayList<Agenda>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecao = "SELECT CODIGO, NOMEMEDICAMENTOAGENDA, ESTOQUEAGENDA, UMASEMANA, TRESDIAS, UMDIA, DIA, MES, ANO FROM AGENDA";
        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
                Agenda agenda = new Agenda();
                agenda.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                agenda.setNomeMedicamentoAgenda(cursor.getString(cursor.getColumnIndex("NOMEMEDICAMENTOAGENDA")));
                agenda.setEstoqueAgenda(cursor.getInt(cursor.getColumnIndex("ESTOQUEAGENDA")));
                agenda.setUmaSemana(cursor.getInt(cursor.getColumnIndex("UMASEMANA")));
                agenda.setTresDias(cursor.getInt(cursor.getColumnIndex("TRESDIAS")));
                agenda.setUmDia(cursor.getInt(cursor.getColumnIndex("UMDIA")));
                agenda.setDia(cursor.getInt(cursor.getColumnIndex("DIA")));
                agenda.setMes(cursor.getInt(cursor.getColumnIndex("MES")));
                agenda.setAno(cursor.getInt(cursor.getColumnIndex("ANO")));
                agendas.add(agenda);
                temProximo = cursor.moveToNext();
            }
        }
        return agendas;
    }
}
