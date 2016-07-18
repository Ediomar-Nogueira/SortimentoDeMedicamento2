package br.edu.ifba.mobile.sortimentodemedicamento.fragmentoAgenda;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import br.edu.ifba.mobile.sortimentodemedicamento.R;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Agenda;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasAgenda.GravacaoAgenda;

/**
 * Created by Edigar on 08/07/2016.
 */
public class FragmentoCadastroAgenda extends Fragment {
    private static FragmentoCadastroAgenda instancia = null;
    public static FragmentoCadastroAgenda getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroAgenda();
        }
        return instancia;
    }

    private View tela = null;
    private CheckBox umaSemanaBox=null;
    private CheckBox tresDiasBox=null;
    private CheckBox umDiaBox=null;
    private int umaSemana=0;
    private int tresDias=0;
    private int umDia=0;
    //private TextView diaM = null;
   // private TextView mesM = null;
   // private TextView anoM = null;
    private EditText nomeMedicamentoAgenda = null;
    private EditText estoqueAgenda = null;

    private Button btnGravar2 = null;
    private Agenda agenda = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_agenda, vgrupo, false);
        preparar(); // Cria a toda a parte dos Checkbox
        return tela;

    }

    private void preparar() {
        umaSemanaBox = (CheckBox) tela.findViewById(R.id.umaSemana);
        tresDiasBox = (CheckBox) tela.findViewById(R.id.tresDias);
        umDiaBox = (CheckBox) tela.findViewById(R.id.umDia);
        umaSemanaBox.setOnCheckedChangeListener(myCheckboxListener);
        tresDiasBox.setOnCheckedChangeListener(myCheckboxListener);
        umDiaBox.setOnCheckedChangeListener(myCheckboxListener);
        nomeMedicamentoAgenda = (EditText) tela.findViewById(R.id.nomeMedicamentoAgenda);
        estoqueAgenda = (EditText) tela.findViewById(R.id.estoqueAgenda);

        btnGravar2 = (Button) tela.findViewById(R.id.btnGravar2);

        btnGravar2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               GravacaoAgenda gravacao = new GravacaoAgenda(getContexto(), getAgenda());
               gravacao.execute();
               limparCampos();
              }
          });
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Agenda getAgenda() {
        agenda.setUmaSemana(Integer.valueOf(umaSemana));
        agenda.setTresDias(Integer.valueOf(tresDias));
        agenda.setUmDia(Integer.valueOf(umDia));

        agenda.setNomeMedicamentoAgenda(nomeMedicamentoAgenda.getText().toString());
        agenda.setEstoqueAgenda(Integer.valueOf(estoqueAgenda.getText().toString()));

        return agenda;
    }
    //

    public void exibirAgendaSelecionada() {
        agenda = FragmentoListaAgenda.getInstancia().getAgendaSelecionada();
        if (agenda.getCodigo() == -1) {
            limparCampos();
            limparChekbox();

        } else {
            limparChekbox();
            carregarCampos();
            setarCheckbox();
        }
    }

    public void limparChekbox(){
        umaSemanaBox.setChecked(false);
        tresDiasBox.setChecked(false);
        umDiaBox.setChecked(false);
        umaSemana = 0;
        tresDias = 0;
        umDia =0;
    }



    public void setarCheckbox(){
        if (umaSemana == 7) {
            umaSemanaBox.setChecked(true);

            tresDias = 0;
            umDia =0;
        }
        if (tresDias == 3) {
            tresDiasBox.setChecked(true);

            umaSemana =0;
            umDia =0;
        }
        if (umDia == 1) {
            umDiaBox.setChecked(true);

            umaSemana =0;
            tresDias =0;
        }
    }

    private void limparCampos() {
        nomeMedicamentoAgenda.setText("");
        estoqueAgenda.setText("0");
        umaSemana= 0;
        tresDias = 0;
        umDia = 0;
        limparChekbox();


    }

    private void carregarCampos() {

        nomeMedicamentoAgenda.setText(agenda.getNomeMedicamentoAgenda());
        estoqueAgenda.setText(agenda.getEstoqueAgenda()+"");
        umaSemana = Integer.parseInt((agenda.getUmaSemana()+""));
        tresDias = Integer.parseInt((agenda.getTresDias()+""));
        umDia =  Integer.parseInt((agenda.getUmDia()+""));
         //diaM.setText(agenda.getDia()+"");
       // mesM.setText(agenda.getMes()+"");
       // anoM.setText(agenda.getAno()+"");
        setarCheckbox();
    }
    private CompoundButton.OnCheckedChangeListener myCheckboxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            switch (buttonView.getId()) {
                case R.id.umaSemana:
                    umaSemana = 7;
                    //Se você marca uma caixa as outras se desmarcam
                    if (isChecked == true) {
                        tresDiasBox.setChecked(false);
                        umDiaBox.setChecked(false);

                    } else if (isChecked == false) {
                        umaSemana = 0;
                    }

                    break;
                case R.id.tresDias:
                    tresDias = 3;
                    if (isChecked == true) {
                        umaSemanaBox.setChecked(false);
                        umDiaBox.setChecked(false);
                    } else if (isChecked == false) {
                        tresDias = 0;
                    }

                    break;
                case R.id.umDia:
                    umDia = 1;
                    if (isChecked == true) {
                        umaSemanaBox.setChecked(false);
                        tresDiasBox.setChecked(false);
                    } else if (isChecked == false) {
                        umDia = 0;
                    }

                    break;
                default:
                    break;
            }
        }
    };


}




