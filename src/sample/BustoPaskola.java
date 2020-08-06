package sample;

public class BustoPaskola {
    double pDydis;
    int pTrukme;
    private String tipas;
    double menSuma;
    double pProcentas;
    BustoPaskola(double pDydis, int pTrukme, double pProcentas)
    {  
        this.pDydis = pDydis;
        this.pTrukme = pTrukme;
        this.pProcentas = pProcentas;
    }
    double getPDydis()
    {
        return pDydis;
    }
    int getPTrukme()
    {
        return pTrukme;
    }
    String getTipas()
    {
        return tipas;
    }
    double getMenSuma()
    {
        return menSuma;
    }
    void calcSuma()
    {
        
    }

}

