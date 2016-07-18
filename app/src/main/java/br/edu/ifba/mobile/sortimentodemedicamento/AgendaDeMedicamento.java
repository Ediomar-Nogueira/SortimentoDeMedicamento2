package br.edu.ifba.mobile.sortimentodemedicamento;

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

import br.edu.ifba.mobile.sortimentodemedicamento.db.FachadaAgendaBD;
import br.edu.ifba.mobile.sortimentodemedicamento.fragmentoAgenda.FragmentoCadastroAgenda;
import br.edu.ifba.mobile.sortimentodemedicamento.fragmentoAgenda.FragmentoListaAgenda;

public class AgendaDeMedicamento extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ImageButton chamaMedicamento;


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
        setContentView(R.layout.activity_agenda_de_medicamento);

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
        FachadaAgendaBD.criarInstancia(this.getApplicationContext());

        chamaMedicamento =(ImageButton)findViewById(R.id.btnChamaMedicamento);
        chamaMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position ==0) {
            FragmentoCadastroAgenda.getInstancia().exibirAgendaSelecionada();


        } else if (position == 1) {
            FragmentoListaAgenda.getInstancia().atualizar();



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
                    frag = FragmentoCadastroAgenda.getInstancia();
                    break;
                case 1:
                    frag = FragmentoListaAgenda.getInstancia();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Cadastro Agenda";
                case 1:
                    return "Listagem";

            }
            return null;
        }
    }
}
