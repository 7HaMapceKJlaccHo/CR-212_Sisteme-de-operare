package com.example.laborator_1;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

// Clasa TimerManager se ocupă de gestionarea timerelor pentru diferite operații.
public class TimerManager {

    private Timer timer; // Timer-ul folosit pentru programarea sarcinilor.
    private Stage secondaryStage; // Fereastra secundară pentru afișarea stării operațiilor.
    private final UIManager uiManager; // Referința către managerul de UI pentru interacțiune.

    // Constructorul clasei TimerManager.
    public TimerManager(UIManager uiManager) {
        // Inițializăm referința către UIManager.
        this.uiManager = uiManager;
    }

    // Metoda care execută o operație specificată de numărul primit ca parametru.
    public void performOperation(int operationNum) {
        // Închidem fereastra secundară dacă este deja deschisă.
        uiManager.closeSecondaryStage(secondaryStage);

        // Creăm o nouă fereastră secundară și configurăm interfața.
        secondaryStage = new Stage();
        Label displayLabel = new Label("Operația " + operationNum + " în desfășurare...");
        VBox vbox = new VBox(displayLabel);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 200, 100);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Operația " + operationNum);
        secondaryStage.show(); // Afișăm fereastra secundară.

        // Începem să cronometrăm operația.
        startTimer(operationNum, displayLabel);
    }

    // Metoda pentru inițializarea și pornirea timer-ului.
    private void startTimer(int operationNum, Label displayLabel) {
        // Oprim timer-ul anterior dacă este cazul.
        stopTimer();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Definim textul care va fi afișat în funcție de operație.
                String text = "";
                switch (operationNum) {
                    case 1:
                        text = "Număr aleator: " + Math.random();
                        break;
                    case 2:
                        double randomNumber = Math.random() * 5;
                        text = "Număr curent: " + randomNumber;
                        if (randomNumber < 1) {
                            // Dacă numărul este mai mic decât 1, afișăm o alertă.
                            Platform.runLater(() -> showAlert());
                        }
                        break;
                    case 3:
                        text = "Alt număr: " + Math.random();
                        break;
                }
                // Actualizăm eticheta din fereastra secundară cu textul nou.
                String finalText = text;
                Platform.runLater(() -> uiManager.updateLabel(displayLabel, finalText ));
            }
        };

        // Setăm periodicitatea timer-ului în funcție de operație.
        long period = 1000; // 1 secundă
        if (operationNum == 1 || operationNum == 3) {
            timer.scheduleAtFixedRate(task, 0, period);
        } else if (operationNum == 2) {
            timer.schedule(task, 2000); // O singură execuție după 2 secunde.
        }

        // Pentru Operația 3, oprește timer-ul după 10 secunde.
        if (operationNum == 3) {
            TimerTask stopTask = new TimerTask() {
                @Override
                public void run() {
                    stopTimer();
                }
            };
            timer.schedule(stopTask, 10000); // Programăm oprirea.
        }
    }

    // Metoda pentru afișarea unei alerte în cazul în care este necesar.
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertisment");
        alert.setHeaderText(null);
        alert.setContentText("Numărul generat este mai mic decât 1!");
        alert.showAndWait(); // Așteptăm până utilizatorul închide alerta.
    }

    // Metoda pentru oprirea timer-ului.
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}


