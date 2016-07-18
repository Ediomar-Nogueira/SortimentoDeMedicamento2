package br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.sortimentodemedicamento.db.Agenda;
import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaAgendaBD;

/**
 * Created by Edigar on 15/07/2016.
 */
public class GravacaoAgenda  extends AsyncTask<Void, Void, String> {
    private Context contexto = null;
    private Agenda agenda = null;

    public GravacaoAgenda(Context contexto, Agenda agenda){
        this.agenda = agenda;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo=-1;
        if(agenda.getCodigo()==-1){
            codigo= FachadaAgendaBD.getInstancia().inserir(agenda);
        }else{
            codigo= FachadaAgendaBD.getInstancia().atualizar(agenda);
        }
        if (codigo > 0) {
            mensagem="Agenda gravada com sucesso!";
        }
        else{
            mensagem="Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
