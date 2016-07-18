package br.edu.ifba.mobile.sortimentodemedicamento.fragmentoAgenda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.sortimentodemedicamento.R;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Agenda;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda.ListagemAgenda;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda.RemocaoAgenda;

/**
 * Created by Edigar on 15/07/2016.
 */
public class FragmentoListaAgenda extends Fragment{
    private  static FragmentoListaAgenda instancia = null;

    public  static FragmentoListaAgenda getInstancia(){
        if(instancia == null){
            instancia = new FragmentoListaAgenda();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){
        tela = inflador.inflate(R.layout.fragmento_lista_agenda, vgrupo, false);
        preparar();
        return tela;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu,inflador);
        inflador.inflate(R.menu.menu_controle_agenda, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id= item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id==R.id.cadastro_remover2){
                RemocaoAgenda remocao=new RemocaoAgenda(this.getContext(), this.getAgendaSelecionada());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaAgenda);

        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemAgenda listagem = new ListagemAgenda(this.getContext(),lista);
        listagem.execute();
    }
    public Agenda getAgendaSelecionada(){
        Agenda agenda=new Agenda();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
            agenda = (Agenda) lista.getItemAtPosition(posicao);

        }

        return agenda;
    }

}

