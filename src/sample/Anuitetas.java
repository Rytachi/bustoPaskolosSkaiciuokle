package sample;

import static java.lang.Math.pow;

public class Anuitetas extends BustoPaskola{
    private double likutis;
    public Anuitetas(double pDydis, int pTrukme, double pProcentas) {
        super(pDydis, pTrukme, pProcentas);
        likutis = pDydis;
    }
    double getPaskLik()
    {
        likutis = likutis -(menSuma - likutis*((pProcentas / 12) / 100));
        return likutis;
    }
    @Override
    void calcSuma()
    {
        double i = (pProcentas / 12) / 100;
        menSuma = ((i * pow((1 + i), (pTrukme * 12))) / (pow((1 + i), (pTrukme * 12)) - 1)) * pDydis;
    }
}
