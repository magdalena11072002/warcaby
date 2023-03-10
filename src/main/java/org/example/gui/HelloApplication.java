package org.example.gui;

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
import org.example.data.DwulinioweData;
import org.example.data.HiszpanskieData;
import org.example.data.PolskieData;

/**
 * @author Magdalena Jurkowska and Weronika Chmiela
 * @version 1.0
 *
 * Funkcja uruchamiająca aplikację oraz ustawiająca wartości w zależności od wybranego trybu gry
 */
public class HelloApplication extends Application {

    /**
     * @param option1, option2, option3 - trzy tryby gry do wyboru
     */
    private final Button option1 = new Button("Warcaby polskie");
    private final Button option2 = new Button("Warcaby dwuliniowe");
    private final Button option3 = new Button("Warcaby hiszpańskie");

    /**
     * Funkcja do uruchomienia programu
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Funkcja start dla HelloAplication która ustawia wszystko w oknie początkowym przed wybraniem trybu oraz ustawia dany tryb po jego wybraniu
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(); //pionowo

        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null))); //zmiana koloru tła

        HBox forbuttons = new HBox(); //poziomo

        forbuttons.setSpacing(40);  //spacje miedzy przyciskami
        forbuttons.setAlignment(Pos.CENTER);


        Label welcome = new Label("\nWitamy w warcabach\n");
        Label choice = new Label("Prosze wybrać rodzaj gry:");

        welcome.setFont(Font.font("Bahnschrift", FontWeight.BOLD, 20));
        choice.setFont(Font.font("Bahnschrift", 20));

        //guziki i akcje
        EventHandler<ActionEvent> first = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameWindow.display(new PolskieData());
                stage.hide();

            }
        };
        option1.setOnAction(first);
        EventHandler<ActionEvent> second = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameWindow.display(new DwulinioweData());
                stage.hide();
            }
        };
        option2.setOnAction(second);
        EventHandler<ActionEvent> third = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameWindow.display(new HiszpanskieData());
                stage.hide();
            }
        };
        option3.setOnAction(third);

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

}

