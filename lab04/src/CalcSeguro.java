package lab04.src;

public enum CalcSeguro {
    VALOR_BASE (100.0),
    FATOR_18_30 (1.2),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5);

    public final double fator;
    
    CalcSeguro(double fator) {
        this.fator = fator;
    }

    public static double fatorIdade(int idade) {
        if(18 <= idade && idade < 30) {
            return FATOR_18_30.fator;
        } else if(30 <= idade && idade < 60) {
            return FATOR_30_60.fator;
        } else if(60 <= idade && idade < 90) {
            return FATOR_60_90.fator;
        } else {
            return 0;
        }
    } 
}