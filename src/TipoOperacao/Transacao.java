package TipoOperacao;

import Enums.tipoOperacao;
import Enums.StatusProcessamento;

public class Transacao {

    private String id;
    private double valor;
    private tipoOperacao tipoOperacao;
    private StatusProcessamento statusProcessamento;


    public Transacao(String id, double valor, tipoOperacao tipoOperacao){

        this.tipoOperacao = tipoOperacao;

        this.tipoOperacao.calcularTaxa(valor);

        this.id = id;
        this.valor = valor;
        this.statusProcessamento = StatusProcessamento.CRIADA;

    }

    public void avancarStatus(StatusProcessamento novoStatus){
        if(!this.statusProcessamento.checagemTransicao(novoStatus)){
            throw new RuntimeException("Transição ilegal: de " + this.statusProcessamento + " para " + novoStatus);
        }

        this.statusProcessamento = novoStatus;
        System.out.println("Transação " + id + " alterada para: " + this.statusProcessamento);
    }

    public double valorTotal(){
         double n = tipoOperacao.calcularTaxa(this.valor);
        return this.valor + n;
    }


}
