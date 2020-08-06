package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class MetuImokos {
 
        private final SimpleIntegerProperty menesis;
        private final SimpleDoubleProperty likutis;
        private final SimpleDoubleProperty menSuma;
 
         MetuImokos(int menesis, double likutis, double menSuma) {
            this.menesis = new SimpleIntegerProperty(menesis);
            this.likutis = new SimpleDoubleProperty(likutis);
            this.menSuma = new SimpleDoubleProperty(menSuma);
        }
 
        public int getMenesis() {
            return menesis.get();
        }
 
        public void setMetuNr(int menesis) {
            this.menesis.set(menesis);
        }
 
        public double getLikutis() {
            return likutis.get();
        }
 
        public void setLikutis(double likutis) {
            this.likutis.set(likutis);
        }

        public double getMenSuma() {
            return menSuma.get();
        }
 
        public void setMenSuma(double menSuma) {
            this.menSuma.set(menSuma);
        }

    }