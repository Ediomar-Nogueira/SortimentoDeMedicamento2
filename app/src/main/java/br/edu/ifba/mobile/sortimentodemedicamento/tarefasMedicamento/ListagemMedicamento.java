package br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaMedicamentoBD;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Medicamento;

/**
 * Created by Edigar on 07/07/2016.
 */
public class ListagemMedicamento extends AsyncTask<Void, Void, List<Medicamento>>{

    private Context contexto = null;
    private ListView listaMedicamentos = null;

    public ListagemMedicamento(Context contexto, ListView listaMedicamentos){
        this.listaMedicamentos= listaMedicamentos;
        this.contexto = contexto;

    }

    @Override
    protected List<Medicamento> doInBackground(Void... params) {
        List<Medicamento> medicamentos = FachadaMedicamentoBD.getInstancia().listarMedicamentos();
        return medicamentos;
    }

    @Override
    protected void onPostExecute(List<Medicamento> medicamentos){
        if(medicamentos.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Cadastre um medicamento.", Toast.LENGTH_LONG).show();
           listaMedicamentos.setAdapter(null);
        }
        else{

            ArrayAdapter<Medicamento> adaptador = new ArrayAdapter<Medicamento>(contexto,android.R.layout.simple_list_item_activated_1,medicamentos);//colcoar a lista de disciplina dentro do listview
            listaMedicamentos.setAdapter(adaptador);
        }
    }
}
