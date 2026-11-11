package Enums;

public enum tipoOperacao {

    PIX(0.0, 10000.00){
        @Override
        public double calcularTaxa(double valor){
            if(valor >= getLimiteMaximo()){
                throw new IllegalArgumentException("Limite maximo atingido!");
            }

            return valor;
        }
    },
    TED(10.00, 50000.00){
        @Override
        public double calcularTaxa(double valor){

            if (valor >= getLimiteMaximo()){
                return getTaxaPercentual();
            }

            if(valor > 5000.00){
                return getTaxaPercentual();
            }

            return 0.0;
        }
    },
    CARTAO_CREDITO(0.03, 5000.00){
        @Override
        public double calcularTaxa(double valor){
            if(valor >= getLimiteMaximo()){
                throw new IllegalArgumentException("Limite maximo atingido!");
            }
            return valor * getTaxaPercentual();
        }
    };

    private double taxaPercentual;
    private double limiteMaximo;

    tipoOperacao(double taxaPercentual, double limiteMaximo){
        this.taxaPercentual = taxaPercentual;
        this.limiteMaximo = limiteMaximo;
    }

    public abstract double calcularTaxa(double valor);

    public double getTaxaPercentual(){
        return taxaPercentual;
    }

    public double getLimiteMaximo() {
        return limiteMaximo;
    }
}
