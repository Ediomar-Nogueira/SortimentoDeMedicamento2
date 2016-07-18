package br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento;

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
import br.edu.ifba.mobile.sortimentodemedicamento.db.Medicamento;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento.ListagemMedicamento;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento.RemocaoMedicamento;

/**
 * Created by Edigar on 07/07/2016.
 */
public class FragmentoListaMedicamento extends Fragment {

    private  static FragmentoListaMedicamento instancia = null;

    public  static FragmentoListaMedicamento getInstancia(){
        if(instancia == null){
            instancia = new FragmentoListaMedicamento();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){ //pega o arquivo .xml e transforma em arquivo na memoria
        tela = inflador.inflate(R.layout.fragmento_lista_medicamento, vgrupo, false);
        preparar();
        return tela;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){//cria o menu graficamente
        super.onCreateOptionsMenu(menu,inflador);
        inflador.inflate(R.menu.menu_controle_medicamentos, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){//qual a ação a depender do clic
        long id= item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id==R.id.cadastro_remover){
                RemocaoMedicamento remocao=new RemocaoMedicamento(this.getContext(), this.getMedicamentoSelecionado());
                remocao.execute();

                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaMedicamento);

        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemMedicamento listagem = new ListagemMedicamento(this.getContext(),lista);
        listagem.execute();
    }
    public Medicamento getMedicamentoSelecionado(){
        Medicamento medicamento=new Medicamento();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
            medicamento = (Medicamento) lista.getItemAtPosition(posicao);

        }

        return medicamento;
    }

}
