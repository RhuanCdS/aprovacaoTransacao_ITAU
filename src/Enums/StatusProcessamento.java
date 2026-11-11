package Enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum StatusProcessamento {

    CRIADA {
        @Override
        public void mensagem(){
            System.out.println("Status: CRIADA");
        }
    },
    EM_ANALISE {
        @Override
        public void mensagem(){
            System.out.println("Status: Em analise");
        }
    },
    APROVADA {
        @Override
        public void mensagem(){
            System.out.println("Status: Aprovada");
        }
    },
    REJEITADA {
        @Override
        public void mensagem(){
            System.out.println("Status: Rejeitada");
        }
    };

    private static final Map<StatusProcessamento, Set<StatusProcessamento>> STATUS;

    static {
        STATUS = new EnumMap<>(StatusProcessamento.class);
        STATUS.put(CRIADA, EnumSet.of(EM_ANALISE, REJEITADA));
        STATUS.put(EM_ANALISE, EnumSet.of(APROVADA, REJEITADA));
        STATUS.put(APROVADA, EnumSet.noneOf(StatusProcessamento.class));
        STATUS.put(REJEITADA, EnumSet.noneOf(StatusProcessamento.class));
    }

    public abstract void mensagem();

    public boolean checagemTransicao(StatusProcessamento status){
        if(!STATUS.get(this).contains(status)){
            throw new IllegalArgumentException("Não é permitido essa operação -> transição de status ilegal");
        }

        return true;
    }
}
