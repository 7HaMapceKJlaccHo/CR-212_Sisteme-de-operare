package com.example.laborator_1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Clasa UIManager se ocupă de crearea și gestionarea interfeței grafice a utilizatorului.
public class UIManager {

    // Variabilă pentru stocarea ferestrei principale.
    private final Stage primaryStage;
    // Variabilă pentru stocarea managerului de timere.
    private final TimerManager timerManager;

    // Constructorul clasei UIManager.
    public UIManager(Stage stage) {
        // Inițializăm fereastra principală și managerul de timere.
        this.primaryStage = stage;
        this.timerManager = new TimerManager(this);
    }

    // Metoda pentru inițializarea interfeței grafice.
    public void initUI() {
        // Creăm un container vertical cu spațierea dintre elemente de 10.
        VBox root = new VBox(10);
        // Aliniem elementele containerului la centru.
        root.setAlignment(Pos.CENTER);

        // Creăm o etichetă cu un text inițial.
        Label label = new Label("Gata...");

        // Creăm butoane pentru diferite operații.
        Button operation1Button = new Button("Operația 1");
        Button operation2Button = new Button("Operația 2");
        Button operation3Button = new Button("Operația 3");

        // Setăm acțiuni pentru butoane care vor apela metode din TimerManager.
        operation1Button.setOnAction(e -> timerManager.performOperation(1));
        operation2Button.setOnAction(e -> timerManager.performOperation(2));
        operation3Button.setOnAction(e -> timerManager.performOperation(3));

        // Adăugăm eticheta și butoanele în container.
        root.getChildren().addAll(label, operation1Button, operation2Button, operation3Button);

        // Creăm o scenă cu containerul nostru, stabilim dimensiunile și o adăugăm la fereastra principală.
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Timere JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show(); // Afișăm fereastra principală.
    }

    // Metodă ajutătoare pentru actualizarea textului unei etichete.
    public void updateLabel(Label label, String text) {
        label.setText(text);
    }

    // Metodă ajutătoare pentru închiderea unei ferestre secundare.
    public void closeSecondaryStage(Stage stage) {
        // Verificăm dacă fereastra secundară este deschisă și, dacă da, o închidem.
        if (stage != null) {
            stage.close();
        }
    }

}