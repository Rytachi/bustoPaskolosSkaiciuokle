package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Main extends Application {

    private ComboBox comboBox;
    private TextField pTrukme;
    private TextField pDydis;
    private TextField pProcentas;
    private Label label;

    @Override
    public void start(Stage var1) {
        this.label = new Label();
        this.pDydis = new TextField();
        this.pTrukme = new TextField();
        this.pProcentas = new TextField();
        Button button = new Button("Skaiciuoti");
        this.comboBox = new ComboBox();
        this.pDydis.setPromptText("Paskolos Dydis");
        this.pTrukme.setPromptText("Paskolos Trukme(Metais)");
        this.pProcentas.setPromptText("Procento dydis");
        this.comboBox.getItems().addAll(new String[]{"Anuiteto", "Linijinis"});
        this.comboBox.setPromptText("Kokio paskolos tipo norite?");
        button.setOnAction((var1x) -> {
            if (!"".equals(this.pDydis.getText()) && this.TikrintiArSkaicius(this.pDydis.getText()) && Double.parseDouble(this.pDydis.getText()) >= 10000.0D) {
                if (this.TikrintiArSveikasis(this.pTrukme) && Integer.parseInt(this.pTrukme.getText()) >= 1 && Integer.parseInt(this.pTrukme.getText()) <= 30) {
                    if (!"".equals(this.pProcentas.getText()) && this.TikrintiArSkaicius(this.pProcentas.getText()) && Double.parseDouble(this.pProcentas.getText()) >= 1.0D && Double.parseDouble(this.pProcentas.getText()) <= 10.0D) {
                        if (!"Anuiteto".equals(this.comboBox.getValue()) && !"Linijinis".equals(this.comboBox.getValue())) {
                            this.label.setText("Blogai pasirinkta paskolos rusis.");
                            this.label.setTextFill(Color.web("#ff0000"));
                        } else {
                            try {
                                String var2 = "Ataskaita.txt";
                                File var3 = new File(var2);
                                if (!var3.exists()) {
                                    var3.createNewFile();
                                }

                                FileWriter var4 = new FileWriter(var3.getAbsoluteFile());
                                BufferedWriter var5 = new BufferedWriter(var4);
                                var5.write(Lygiavimas("Menesis", 10) + Lygiavimas("Likutis", 15) + Lygiavimas("Men. Imoka", 10));
                                var5.newLine();
                                this.label.setText("");
                                ObservableList var6;
                                if ("Anuiteto".equals(this.comboBox.getValue())) {
                                    var6 = FXCollections.observableArrayList();
                                    Anuitetas var12 = new Anuitetas(Double.parseDouble(this.pDydis.getText()), Integer.parseInt(this.pTrukme.getText()), Double.parseDouble(this.pProcentas.getText()));
                                    var12.calcSuma();

                                    for(int var8 = 1; var8 <= var12.getPTrukme() * 12; ++var8) {
                                        double var13 = this.Apvalinti(var12.getPaskLik());
                                        var5.write(Lygiavimas(Integer.toString(var8), 13) + Lygiavimas(String.valueOf(var13), 15) + Lygiavimas(String.valueOf(this.Apvalinti(var12.getMenSuma())), 15));
                                        var5.newLine();
                                        var6.add(new MetuImokos(var8, var13, this.Apvalinti(var12.getMenSuma())));
                                    }

                                    AlertBox.display(var6);
                                } else {
                                    var6 = FXCollections.observableArrayList();
                                    double var7 = 0.0D;
                                    Linijinis var9 = new Linijinis(Double.parseDouble(this.pDydis.getText()), Integer.parseInt(this.pTrukme.getText()), Double.parseDouble(this.pProcentas.getText()));

                                    for(int var10 = 1; var10 <= var9.getPTrukme() * 12; ++var10) {
                                        var9.calcSuma();
                                        var5.write(Lygiavimas(Integer.toString(var10), 15) + Lygiavimas(String.valueOf(this.Apvalinti(var9.getPaskLik())), 15) + Lygiavimas(String.valueOf(this.Apvalinti(var9.getMenSuma())), 15));
                                        var5.newLine();
                                        var6.add(new MetuImokos(var10, this.Apvalinti(var9.getPaskLik()), this.Apvalinti(var9.getMenSuma())));
                                        var7 += this.Apvalinti(var9.getMenSuma());
                                    }

                                    AlertBox.display(var6);
                                }

                                var5.close();
                            } catch (Exception var11) {
                                System.out.println(var11);
                            }
                        }
                    } else {
                        this.label.setText("Blogai ivestas paskolos procentas.");
                        this.label.setTextFill(Color.web("#ff0000"));
                    }
                } else {
                    this.label.setText("Blogai ivesta paskolos trukme.");
                    this.label.setTextFill(Color.web("#ff0000"));
                }
            } else {
                this.label.setText("Blogai ivestas paskolos dydis. Min - 10000, Max - 1000000.");
                this.label.setTextFill(Color.web("#ff0000"));
            }

        });
        VBox var2 = new VBox(10.0D);
        var2.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        var2.getChildren().addAll(this.pDydis, this.pTrukme, this.pProcentas, this.comboBox, button, this.label);
        var1.setTitle("BustoPaskolosSkaiciuokle");
        Scene scene = new Scene(var2, 400.0D, 300.0D);
        var1.setScene(scene);
        var1.show();
    }


    private double Apvalinti(double var1) {
        var1 = (double)Math.round(var1 * 100.0D);
        var1 /= 100.0D;
        return var1;
    }

    private static String Lygiavimas(String var0, int var1) {
        return String.format("%1$-" + var1 + "s", var0);
    }

    private boolean TikrintiArSkaicius(String var1) {
        NumberFormat var2 = NumberFormat.getInstance();
        ParsePosition var3 = new ParsePosition(0);
        var2.parse(var1, var3);
        return var1.length() < 100000000 && var1.length() == var3.getIndex();
    }

    private boolean TikrintiArSveikasis(TextField var1) {
        try {
            int var2 = Integer.parseInt(var1.getText());
            return true;
        } catch (NumberFormatException var3) {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
