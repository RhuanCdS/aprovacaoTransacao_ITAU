package Main;

import Enums.StatusProcessamento;
import Enums.tipoOperacao;
import TipoOperacao.Transacao;

public class Main{

    public static void main(String[] args){
                System.out.println("=== TESTE 1: Transação PIX com Sucesso ===");
                try {
                    Transacao pix = new Transacao("TX-1001", 1500.00, tipoOperacao.PIX);
                    System.out.println("Transação criada com sucesso.");
                    System.out.println("Valor total com taxa: R$ " + pix.valorTotal());

                    // Transição permitida: CRIADA -> EM_ANALISE
                    pix.avancarStatus(StatusProcessamento.EM_ANALISE);

                    // Transição permitida: EM_ANALISE -> APROVADA
                    pix.avancarStatus(StatusProcessamento.APROVADA);
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                System.out.println("\n=== TESTE 2: Limite Excedido no Cartão de Crédito ===");
                try {
                    // Limite do Cartão é 5000.00 -> Deve disparar Exceção no construtor
                    Transacao cartao = new Transacao("TX-1002", 7000.00, tipoOperacao.CARTAO_CREDITO);
                } catch (Exception e) {
                    System.out.println("Exceção Capturada (esperado): " + e.getMessage());
                }

                System.out.println("\n=== TESTE 3: Transição Ilegal de Status ===");
                try {
                    Transacao ted = new Transacao("TX-1003", 2000.00, tipoOperacao.TED);
                    // Tentativa de pular de CRIADA direto para APROVADA (Ilegal!)
                    ted.avancarStatus(StatusProcessamento.APROVADA);
                } catch (Exception e) {
                    System.out.println("Exceção Capturada (esperado): " + e.getMessage());
                }

    }
}
