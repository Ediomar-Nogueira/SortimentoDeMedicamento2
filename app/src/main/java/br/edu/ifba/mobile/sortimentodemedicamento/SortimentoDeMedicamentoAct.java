package br.edu.ifba.mobile.sortimentodemedicamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaMedicamentoBD;
import br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento.FragmentoCadastroMedicamento;
import br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento.FragmentoListaMedicamento;
import br.edu.ifba.mobile.sortimentodemedicamento.fragmentoMedicamento.FragmentoTelaInformacao;

public class SortimentoDeMedicamentoAct extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ImageButton chamaAgenda;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortimento_de_medicamento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        paginador.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(paginador);

        paginador.addOnPageChangeListener(this);
        FachadaMedicamentoBD.criarInstancia(this.getApplicationContext());

        chamaAgenda =(ImageButton)findViewById(R.id.btnChamaAgenda);
        chamaAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SortimentoDeMedicamentoAct.this, AgendaDeMedicamento.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            FragmentoCadastroMedicamento.getInstancia().exibirMedicamentoSelecionado();
        } else if (position == 2) {
            FragmentoListaMedicamento.getInstancia().atualizar();


        }


    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;
            switch (position){
                case 0:
                    frag = FragmentoTelaInformacao.getInstancia();
                    break;
                case 1:
                    frag = FragmentoCadastroMedicamento.getInstancia();
                    break;
                case 2:
                    frag =  FragmentoListaMedicamento.getInstancia();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informações";
                case 1:
                    return "Cadastro";
                case 2:
                    return "Medicamentos";
            }
            return null;
        }
    }
}
