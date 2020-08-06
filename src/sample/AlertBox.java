package sample;

import static java.lang.Integer.parseInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class AlertBox {
    static ObservableList data1;
    static void display(ObservableList data) {
        Button atnaujintimoMygtukas = new Button("Atnaujinti");

        TextField min = new TextField();
        TextField max = new TextField();
        Label label = new Label();
        min.setPromptText("Nuo");
        max.setPromptText("Iki");
        Stage window = new Stage();
        Scene scene = new Scene(new Group());
        data1 = FXCollections.observableArrayList(data);

        window.setTitle("BustoPaskola");

        label.setText("Didžiausias Galimas intervalas: (1;"+data.size()+")");
        label.setTextFill(Color.web("#ff0000"));

        TableColumn<MetuImokos, Integer> menesioStulpelis = new TableColumn<>("Menesis");
        menesioStulpelis.setCellValueFactory(new PropertyValueFactory<>("menesis"));
        menesioStulpelis.setMinWidth(120);

        TableColumn<MetuImokos, Double> likucioStulpelis = new TableColumn<>("Likutis");
        likucioStulpelis.setCellValueFactory(new PropertyValueFactory<>("likutis"));
        likucioStulpelis.setMinWidth(180);

        TableColumn<MetuImokos, Double> imokosStulpelis = new TableColumn<>("Imoka");
        imokosStulpelis.setCellValueFactory(new PropertyValueFactory<>("menSuma"));
        imokosStulpelis.setMinWidth(180);

        TableView<MetuImokos> lentele = new TableView<MetuImokos>(data1);
        lentele.getColumns().addAll(menesioStulpelis, likucioStulpelis,imokosStulpelis);
        lentele.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        atnaujintimoMygtukas.setOnAction(e ->{
            lentele.getItems().clear();
            ObservableList data2 = Filtruoti(data, Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
            lentele.getItems().addAll(data2);
        });

        final HBox hbox = new HBox();
        hbox.getChildren().addAll(min, max, atnaujintimoMygtukas, label);
        final VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, lentele);
        VBox.setVgrow(hbox, Priority.ALWAYS);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        window.setScene(scene);
        window.show();
    }
    
    private static ObservableList Filtruoti (ObservableList data1, int interval1, int interval2){
        ObservableList data = FXCollections.observableArrayList(data1);
        data.remove(interval2, data.size());
        data.remove(0, interval1 - 1);

        return data;
    }

        
}