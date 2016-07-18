package br.edu.ifba.mobile.sortimentodemedicamento.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Edigar on 08/07/2016.
 */
public class Agenda {

    private long codigo = -1;
    private String nomeMedicamentoAgenda;
    private int estoqueAgenda = 0;
    private int umaSemana;
    private int tresDias;
    private int umDia;


    Calendar cal = Calendar.getInstance();
    int dia = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    int ano = cal.get(Calendar.DAY_OF_MONTH);




    public long getCodigo(){
        return codigo;
    }
    public void setCodigo(long codigo){
        this.codigo = codigo;
    }
    public String getNomeMedicamentoAgenda(){
        return nomeMedicamentoAgenda;
    }
    public void setNomeMedicamentoAgenda(String nomeMedicamentoAgenda){
        this.nomeMedicamentoAgenda = nomeMedicamentoAgenda;
    }
    public int getEstoqueAgenda(){
        return estoqueAgenda;
    }
    public void setEstoqueAgenda(int estoqueAgenda){
        this.estoqueAgenda = estoqueAgenda;
    }
    public int getUmaSemana(){
        return umaSemana;
    }
    public void setUmaSemana(int umaSemana){
        this.umaSemana = umaSemana;
    }
    public int getTresDias(){
        return tresDias;
    }
    public void setTresDias(int tresDias){
        this.tresDias = tresDias;
    }
    public int getUmDia(){
        return umDia;
    }
    public void setUmDia(int umDia){
        this.umDia = umDia;
    }
    public int getDia(){
        return dia;
    }
    public void setDia(int dia){
        this.dia = dia;
    }
    public int getMes(){
        return mes;
    }
    public void setMes(int mes){
        this.mes = mes;
    }
    public int getAno(){
        return ano;
    }
    public void setAno(int ano){
        this.ano = ano;
    }


    public void EstoqueNoFim(){

    }

    public void EstoqueEsgotado(){

    }

    @Override
    public String toString(){

        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCadastro = new GregorianCalendar(dia, mes, ano);
        Calendar dataNotificacao = new GregorianCalendar(dia, mes, ano);
        Calendar dataFimEstoque = new GregorianCalendar(dia, mes, ano);

        int notifica = (umaSemana+tresDias+umDia);

        dataNotificacao.add(Calendar.DAY_OF_MONTH, (estoqueAgenda-notifica));
        dataFimEstoque.add(Calendar.DAY_OF_MONTH,estoqueAgenda);

        if (data.format(dataCadastro.getTime()) == data.format(dataNotificacao.getTime()) ){

        }
        return "Medicamento: "+ nomeMedicamentoAgenda +"\nDuração no Estoque: "+ estoqueAgenda +" Dias" + "\n Data de Cadastro: "+ data.format(dataCadastro.getTime())+
        "\n Termino do Estoque: "+ data.format(dataFimEstoque.getTime())+ "\n Data de Notificação: "+ data.format(dataNotificacao.getTime());
    }
}
