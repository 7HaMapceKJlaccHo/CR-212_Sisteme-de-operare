package com.example.laborator_1;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        switch (operationNum) {
            case 1:
                timer = new Timer();
                TimerTask task1 = new TimerTask() {
                    @Override
                    public void run() {
                        // Generăm un număr aleator și îl afișăm.
                        String text = "Număr aleator: " + Math.random();
                        Platform.runLater(() -> uiManager.updateLabel(displayLabel, text));
                    }
                };
                // Programăm sarcina pentru a fi executată la fiecare secundă.
                timer.scheduleAtFixedRate(task1, 0, 1000);
                break;
            case 2:
                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        // Afișăm data și ora locală după 5 secunde.
                        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        Platform.runLater(() -> uiManager.updateLabel(displayLabel, dateTime));
                    }
                };
                // Programăm sarcina pentru a fi executată o singură dată, după 5 secunde.
                timer = new Timer();
                timer.schedule(task2, 5000);
                break;
            case 3:
                String greetingText = "Buna";
                Platform.runLater(() -> uiManager.updateLabel(displayLabel, greetingText));
                TimerTask task3 = new TimerTask() {
                    @Override
                    public void run() {
                        // După 10 secunde, ștergem textul din interfață.
                        Platform.runLater(() -> uiManager.updateLabel(displayLabel, ""));
                    }
                };
                // Programăm sarcina pentru a fi executată o singură dată, după 10 secunde.
                timer = new Timer();
                timer.schedule(task3, 10000);
                break;
            default:
                // Pentru operațiile necunoscute, nu facem nimic.
                break;
        }
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
