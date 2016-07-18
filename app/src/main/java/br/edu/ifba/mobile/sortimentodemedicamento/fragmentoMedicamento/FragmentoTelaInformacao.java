package br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifba.mobile.sortimentodemedicamento.R;

/**
 * Created by Edigar on 06/07/2016.
 */
public class FragmentoTelaInformacao extends Fragment {

    private static FragmentoTelaInformacao instancia = null;

    public static FragmentoTelaInformacao getInstancia(){
        if (instancia == null){
            instancia = new FragmentoTelaInformacao();
        }
        return instancia;
    }


    private View tela = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_tela_informacao, vgrupo, false);
        return tela;
    }
}
