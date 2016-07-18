package br.edu.ifba.mobile.sortimentodemedicamento.db;

/**
 * Created by Edigar on 07/07/2016.
 */
public class Medicamento {

    private long codigo = -1;
    private String nomeMedicamento;
    private String descricaoMedicamento;
    private int somaEstoque = 0;
    private int dosagemDiaria = 0;
    private String nomeFarmacia;
    private String telefoneFarmacia;
    private int quantidadeAtual = 0;

    public long getCodigo(){
        return codigo;
    }

    public void setCodigo(long codigo){
        this.codigo = codigo;
    }
    public String getNomeMedicamento(){
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento){
        this.nomeMedicamento = nomeMedicamento;
    }
    public String getDescricaoMedicamento(){
        return descricaoMedicamento;
    }

    public void setDescricaoMedicamento(String descricaoMedicamento){
        this.descricaoMedicamento = descricaoMedicamento;
    }
    public int getSomaEstoque(){
        return somaEstoque;
    }

    public void setSomaEstoque (int somaEstoque){
        this.somaEstoque = somaEstoque;
    }
    public int getDosagemDiaria(){
        return dosagemDiaria;
    }

    public void setDosagemDiaria (int dosagemDiaria){
        this.dosagemDiaria = dosagemDiaria;
    }
    public void setNomeFarmacia(String nomeFarmacia){
        this.nomeFarmacia = nomeFarmacia;
    }
    public String getNomeFarmacia(){
        return nomeFarmacia;
    }
    public void setTelefoneFarmacia(String telefoneFarmacia){
        this.telefoneFarmacia = telefoneFarmacia;
    }
    public String getTelefoneFarmacia(){
        return telefoneFarmacia;
    }
    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }

    public void setQuantidadeAtual (int quantidadeAtual){
        this.quantidadeAtual = quantidadeAtual+(somaEstoque);
        somaEstoque = 0;
    }


    @Override
    public String toString(){
        int duracaoMedicamento =0;
        if(quantidadeAtual!=0 && dosagemDiaria !=0){
            duracaoMedicamento = quantidadeAtual/dosagemDiaria;
        }

        if(duracaoMedicamento <2){
            return "Medicamento: "+ nomeMedicamento +" \nUnidades em Estoque: "+quantidadeAtual +" \nPonto de Compra:  "+ nomeFarmacia+
                    " \nDuração média " + duracaoMedicamento + " Dia "+ "\nTelefone de Contato: "+telefoneFarmacia;

        }else {

            return "Medicamento: " + nomeMedicamento + " \nUnidades em Estoque: " + quantidadeAtual + " \nPonto de Compra:  " + nomeFarmacia +
                    " \nDuração média " + duracaoMedicamento + " Dias " + "\nTelefone de Contato: " + telefoneFarmacia;
        }
    }
}
