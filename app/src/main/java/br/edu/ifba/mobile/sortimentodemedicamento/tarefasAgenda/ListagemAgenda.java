package br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.sortimentodemedicamento.db.Agenda;
import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaAgendaBD;

/**
 * Created by Edigar on 15/07/2016.
 */
public class ListagemAgenda extends AsyncTask<Void, Void, List<Agenda>> {

    private Context contexto = null;
    private ListView listaAgendas = null;

    public ListagemAgenda(Context contexto, ListView listaAgendas){
        this.listaAgendas= listaAgendas;
        this.contexto = contexto;

    }

    @Override
    protected List<Agenda> doInBackground(Void... params) {
        List<Agenda> agendas = FachadaAgendaBD.getInstancia().listarAgenda();
        return agendas;
    }

    @Override
    protected void onPostExecute(List<Agenda> agendas){
        if(agendas.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Cadastre um agendamento.", Toast.LENGTH_LONG).show();
            listaAgendas.setAdapter(null);
        }
        else{
            ArrayAdapter<Agenda> adaptador2 = new ArrayAdapter<Agenda>(contexto, android.R.layout.simple_list_item_activated_1,agendas);
            listaAgendas.setAdapter(adaptador2);
        }
    }
}
