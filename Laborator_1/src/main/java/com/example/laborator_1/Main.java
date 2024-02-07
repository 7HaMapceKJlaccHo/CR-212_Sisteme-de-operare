package com.example.laborator_1;

import javafx.application.Application;
import javafx.stage.Stage;

// Clasa Main este punctul de intrare în aplicația JavaFX.
public class Main extends Application {

    // Metoda main este folosită pentru lansarea aplicației.
    public static void main(String[] args) {
        launch(args); // Metoda launch inițiază procesul de lansare a aplicației.
    }

    // Metoda start este apelată după ce aplicația a fost inițializată.
    @Override
    public void start(Stage primaryStage) {
        // Creăm un obiect al clasei UIManager, trimițând fereastra principală ca argument.
        UIManager uiManager = new UIManager(primaryStage);

        // Apelăm metoda initUI a obiectului uiManager pentru a inițializa și afișa interfața utilizator.
        uiManager.initUI();
    }
}
