package br.edu.ifba.mobile.sortimentodemedicamento.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edigar on 07/07/2016.
 */
public class FachadaMedicamentoBD extends SQLiteOpenHelper{

    private static FachadaMedicamentoBD instancia = null;

    public static FachadaMedicamentoBD criarInstancia(Context context){
        if (instancia == null){
            instancia = new FachadaMedicamentoBD(context);
        }
        return instancia;
    }
    public static FachadaMedicamentoBD getInstancia(){
        return instancia;
    }

    private static String NOME_BANCO = "SortimentoDeMedicamento";
    private static int VERSAO_BANCO = 1;

    public FachadaMedicamentoBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_MEDICAMENTOS =
            "CREATE TABLE MEDICAMENTOS("+
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NOMEMEDICAMENTO TEXT, DESCRICAO TEXT, SOMAESTOQUE INTEGER, DOSAGEMDIARIA INTEGER, NOMEFARMACIA TEXT, TELEFONE TEXT, QUANTIDADEATUAL INTEGER, DURACAOMEDICAMENTO REAL)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_MEDICAMENTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }

    //CRUD

    public long inserir(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMEMEDICAMENTO", medicamento.getNomeMedicamento());
        valores.put("DESCRICAO", medicamento.getDescricaoMedicamento());
        valores.put("SOMAESTOQUE", medicamento.getSomaEstoque());
        valores.put("DOSAGEMDIARIA", medicamento.getDosagemDiaria());
        valores.put("NOMEFARMACIA", medicamento.getNomeFarmacia());
        valores.put("TELEFONE", medicamento.getTelefoneFarmacia());
        valores.put("QUANTIDADEATUAL", medicamento.getQuantidadeAtual());
        long codigo = db.insert("MEDICAMENTOS", null, valores);
        return codigo;
    }

    public long atualizar(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("NOMEMEDICAMENTO", medicamento.getNomeMedicamento());
        valores.put("DESCRICAO", medicamento.getDescricaoMedicamento());
        valores.put("SOMAESTOQUE", medicamento.getSomaEstoque());
        valores.put("DOSAGEMDIARIA", medicamento.getDosagemDiaria());
        valores.put("NOMEFARMACIA", medicamento.getNomeFarmacia());
        valores.put("TELEFONE", medicamento.getTelefoneFarmacia());
        valores.put("QUANTIDADEATUAL", medicamento.getQuantidadeAtual());
        long codigo = db.update("MEDICAMENTOS", valores, "CODIGO = " + medicamento.getCodigo(), null);
        return codigo;
    }

    public int remover(Medicamento medicamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MEDICAMENTOS","CODIGO = " + medicamento.getCodigo(), null);
    }

    public List<Medicamento> listarMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecao = "SELECT CODIGO, NOMEMEDICAMENTO, DESCRICAO, SOMAESTOQUE, DOSAGEMDIARIA, NOMEFARMACIA, TELEFONE, QUANTIDADEATUAL, DURACAOMEDICAMENTO FROM MEDICAMENTOS";
        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                medicamento.setNomeMedicamento(cursor.getString(cursor.getColumnIndex("NOMEMEDICAMENTO")));
                medicamento.setDescricaoMedicamento(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
                medicamento.setSomaEstoque(cursor.getInt(cursor.getColumnIndex("SOMAESTOQUE")));
                medicamento.setDosagemDiaria(cursor.getInt(cursor.getColumnIndex("DOSAGEMDIARIA")));
                medicamento.setNomeFarmacia(cursor.getString(cursor.getColumnIndex("NOMEFARMACIA")));
                medicamento.setTelefoneFarmacia(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                medicamento.setQuantidadeAtual(cursor.getInt(cursor.getColumnIndex("QUANTIDADEATUAL")));
                medicamentos.add(medicamento);
                temProximo = cursor.moveToNext();
            }
        }
        return medicamentos;
    }
}
