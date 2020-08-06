package sample;

public class Linijinis extends BustoPaskola{
    private double likutis;
    private double kreditas;
    
    Linijinis(double pDydis, int pTrukme, double pProcentas) {
        super(pDydis, pTrukme, pProcentas);
        likutis = pDydis;
        kreditas = pDydis / 12 / pTrukme;
        kreditas = Math.round(kreditas * 100);
        kreditas = kreditas / 100;
    }
    double getPaskLik()
    {
        return likutis;
    }
    @Override
    void calcSuma()
    {
        double palukanos;
        palukanos = likutis * pProcentas / 12 / 100;
        palukanos = Math.round(palukanos * 100);
        palukanos = palukanos / 100;
        menSuma = kreditas + palukanos;
        
        likutis -= kreditas;
    }
}
