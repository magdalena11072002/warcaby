package org.example.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final Button option1 = new Button("Warcaby polskie");
    private final Button option2 = new Button("Warcaby dwuliniowe");
    private final Button option3 = new Button("Warcaby hiszpańskie");

    @Override
    public void start(Stage stage) {
        //Layouty - zrobilam takie cos, ale zawsze mozna zmienic
        VBox root = new VBox(); //pionowo

        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null))); //zmiana koloru tła

        HBox forbuttons = new HBox(); //poziomo

        forbuttons.setSpacing(40);  //spacje miedzy przyciskami
        forbuttons.setAlignment(Pos.CENTER);


        Label welcome = new Label("\nWitamy w warcabach\n");
        Label choice = new Label("Prosze wybrać rodzaj gry:");

        //welcome.setTextFill(Color.BLACK); //zmiana koloru tekstu
        welcome.setFont(Font.font("Bahnschrift", FontWeight.BOLD, 20));
        choice.setFont(Font.font("Bahnschrift", 20));

        //guziki i akcje
        EventHandler<ActionEvent> oknoprobaEH = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                GameWindow.display();
                stage.hide();

            }
        };
        option1.setOnAction(oknoprobaEH);
        option2.setOnAction(oknoprobaEH); //
        option3.setOnAction(oknoprobaEH); //

        //dodawanie elementow
        forbuttons.getChildren().add(option1);
        forbuttons.getChildren().add(option2);
        forbuttons.getChildren().add(option3);

        root.getChildren().addAll(welcome, choice, forbuttons);

        //dzialanie okna
        stage.setTitle("Warcaby - wybor gry");
        stage.setScene(new Scene(root, 500, 150)); //WIELKOSC okna
        //stage.sizeToScene(); //dopasowuje okno do zawartosci - moze sie przydac pozniej
        stage.show();
    }
    public String getOption1(){
        String name=option1.getText();
        return name;
    }

    public String getOption2(){
        String name=option2.getText();
        return name;
    }

    public String getOption3(){
        String name=option3.getText();
        return name;
    }

}
