package br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifba.mobile.sortimentodemedicamento.R;
import br.edu.ifba.mobile.sortimentodemedicamento.db.Medicamento;
import br.edu.ifba.mobile.sortimentodemedicamento.tarefasMedicamento.GravacaoMedicamento;

/**
 * Created by Edigar on 06/07/2016.
 */
public class FragmentoCadastroMedicamento extends Fragment{

    private static FragmentoCadastroMedicamento instancia = null;

    public static FragmentoCadastroMedicamento getInstancia(){

        if (instancia == null){
            instancia = new FragmentoCadastroMedicamento();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nomeMedicamento = null;
    private EditText descricaoMedicamento = null;
    private EditText somaEstoque = null;
    private EditText dosagemDiaria = null;
    private EditText nomeFarmacia = null;
    private EditText telefoneFarmacia = null;
    private TextView quantidadeAtual = null;

    private Button btnGravar = null;

    private Medicamento medicamento = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cadastro_medicamento, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar() {

        nomeMedicamento = (EditText) tela.findViewById(R.id.nomeMedicamento);
        descricaoMedicamento = (EditText) tela.findViewById(R.id.descricaoMedicamento);
        somaEstoque = (EditText) tela.findViewById(R.id.somaEstoque);
        dosagemDiaria = (EditText) tela.findViewById(R.id.dosagemDiaria);
        nomeFarmacia = (EditText) tela.findViewById(R.id.nomeFarmacia);
        telefoneFarmacia = (EditText) tela.findViewById(R.id.telefoneFarmacia);
        quantidadeAtual = (TextView) tela.findViewById(R.id.quantidadeAtual);

        btnGravar = (Button) tela.findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GravacaoMedicamento gravacao = new GravacaoMedicamento(getContexto(), getMedicamento());
                gravacao.execute();
                limparCampos();

            }
        });
    }
    private Context getContexto(){
        return this.getContext();
    }

    private Medicamento getMedicamento(){
         medicamento = new Medicamento();

        medicamento.setNomeMedicamento(nomeMedicamento.getText().toString());
        medicamento.setDescricaoMedicamento(descricaoMedicamento.getText().toString());
        medicamento.setSomaEstoque(Integer.valueOf(somaEstoque.getText().toString()));
        medicamento.setDosagemDiaria(Integer.valueOf(dosagemDiaria.getText().toString()));
        medicamento.setNomeFarmacia(nomeFarmacia.getText().toString());
        medicamento.setTelefoneFarmacia(telefoneFarmacia.getText().toString());
        medicamento.setQuantidadeAtual(Integer.valueOf(quantidadeAtual.getText().toString()));

        return medicamento;

    }
    public void exibirMedicamentoSelecionado(){
        medicamento = FragmentoListaMedicamento.getInstancia().getMedicamentoSelecionado();
        if(medicamento.getCodigo()==-1){
            limparCampos();
        }else {
            carregarCampos();
        }

    }
    private void limparCampos(){

        nomeMedicamento.setText("");
        descricaoMedicamento.setText("");
        somaEstoque.setText("0");
        dosagemDiaria.setText("0");
        nomeFarmacia.setText("");
        telefoneFarmacia.setText("");
        quantidadeAtual.setText("0");

    }

    private void carregarCampos(){
        nomeMedicamento.setText(medicamento.getNomeMedicamento());
        descricaoMedicamento.setText(medicamento.getDescricaoMedicamento());
        somaEstoque.setText("0");
        dosagemDiaria.setText(medicamento.getDosagemDiaria()+"");
        nomeFarmacia.setText(medicamento.getNomeFarmacia());
        telefoneFarmacia.setText(medicamento.getTelefoneFarmacia());
        quantidadeAtual.setText(medicamento.getQuantidadeAtual()+"");

    }


}
