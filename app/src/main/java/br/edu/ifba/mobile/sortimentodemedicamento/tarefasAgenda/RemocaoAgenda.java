package br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.sortimentodemedicamento.db.Agenda;
import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaAgendaBD;

/**
 * Created by Edigar on 15/07/2016.
 */
public class RemocaoAgenda  extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Agenda agenda = null;

    public RemocaoAgenda (Context contexto, Agenda agenda){
        this.agenda = agenda;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(agenda.getCodigo()!=-1){
            if(FachadaAgendaBD.getInstancia().remover(agenda)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Agenda removida!";
        }else{
            mensagem="Selecione uma agenda!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
