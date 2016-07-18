package br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaMedicamentoBD;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Medicamento;

/**
 * Created by Edigar on 07/07/2016.
 */
public class RemocaoMedicamento extends AsyncTask<Void, Void, String>{

    private Context contexto = null;
    private Medicamento medicamento = null;

    public RemocaoMedicamento (Context contexto, Medicamento medicamento){
        this.medicamento = medicamento;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(medicamento.getCodigo()!=-1){
            if(FachadaMedicamentoBD.getInstancia().remover(medicamento)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Medicamento removido!";
        }else{
            mensagem="Selecione um medicamento!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}
