package br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaMedicamentoBD;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Medicamento;

/**
 * Created by Edigar on 07/07/2016.
 */
public class GravacaoMedicamento extends AsyncTask<Void, Void, String>{
    private Context contexto = null;
    private Medicamento medicamento = null;

    public GravacaoMedicamento(Context contexto, Medicamento medicamento){
        this.medicamento = medicamento;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo=-1;
        if(medicamento.getCodigo()==-1){
            codigo= FachadaMedicamentoBD.getInstancia().inserir(medicamento);
        }else{
            codigo= FachadaMedicamentoBD.getInstancia().atualizar(medicamento);
        }
        if (codigo > 0) {
            mensagem="Medicamento gravado no estoque com sucesso!";
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
