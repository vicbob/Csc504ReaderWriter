package synced;

import backend.ReaderWriter;
import homepage.Main;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label state1;
    public Label inBalance1;
    public Label finalBalance1;

    public Label state2;
    public Label inBalance2;
    public Label finalBalance2;

    public Label state3;
    public Label inBalance3;
    public Label finalBalance3;

    public Label state4;
    public Label inBalance4;
    public Label finalBalance4;

    public Label state5;
    public Label inBalance5;
    public Label finalBalance5;

    public Label state6;
    public Label inBalance6;
    public Label finalBalance6;

    public Label state7;
    public Label inBalance7;
    public Label finalBalance7;

    public Label state8;
    public Label inBalance8;
    public Label finalBalance8;

    public Label state9;
    public Label inBalance9;
    public Label finalBalance9;

    public Label state10;
    public Label inBalance10;
    public Label finalBalance10;

    public Label lbl;
    public HBox hb1;


    public void closeWindow() {
        Main.window.close();
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
//        Main.window.setMaximized(false);
        Main.window.setMaximized(true);
    }

    public void start() {
        clear();
        ReaderWriter.resetBalance();
        ReaderWriter.AddAPennyTask thread1 = new ReaderWriter.AddAPennyTask();
        Thread tm1 = new Thread(new DepositThreadMonitor(thread1, state1, inBalance1, finalBalance1));
        tm1.start();

        ReaderWriter.AddAPennyTask thread2 = new ReaderWriter.AddAPennyTask();
        Thread tm2 = new Thread(new DepositThreadMonitor(thread2, state2, inBalance2, finalBalance2));
        tm2.start();

        ReaderWriter.AddAPennyTask thread3 = new ReaderWriter.AddAPennyTask();
        Thread tm3 = new Thread(new DepositThreadMonitor(thread3, state3, inBalance3, finalBalance3));
        tm3.start();

        ReaderWriter.AddAPennyTask thread4 = new ReaderWriter.AddAPennyTask();
        Thread tm4 = new Thread(new DepositThreadMonitor(thread4, state4, inBalance4, finalBalance4));
        tm4.start();

        ReaderWriter.AddAPennyTask thread5 = new ReaderWriter.AddAPennyTask();
        Thread tm5 = new Thread(new DepositThreadMonitor(thread5, state5, inBalance5, finalBalance5));
        tm5.start();

        ReaderWriter.RemoveAPennyTask thread6 = new ReaderWriter.RemoveAPennyTask();
        Thread tm6 = new Thread(new WithdrawThreadMonitor(thread6, state6, inBalance6, finalBalance6));
        tm6.start();

        ReaderWriter.RemoveAPennyTask thread7 = new ReaderWriter.RemoveAPennyTask();
        Thread tm7 = new Thread(new WithdrawThreadMonitor(thread7, state7, inBalance7, finalBalance7));
        tm7.start();

        ReaderWriter.RemoveAPennyTask thread8 = new ReaderWriter.RemoveAPennyTask();
        Thread tm8 = new Thread(new WithdrawThreadMonitor(thread8, state8, inBalance8, finalBalance8));
        tm8.start();

        ReaderWriter.RemoveAPennyTask thread9 = new ReaderWriter.RemoveAPennyTask();
        Thread tm9 = new Thread(new WithdrawThreadMonitor(thread9, state9, inBalance9, finalBalance9));
        tm9.start();


        ReaderWriter.RemoveAPennyTask thread10 = new ReaderWriter.RemoveAPennyTask();
        Thread tm10 = new Thread(new WithdrawThreadMonitor(thread10, state10, inBalance10, finalBalance10));
        tm10.start();
    }

    private void clear() {
        state1.setText("Has not deposited");
        inBalance1.setText("");
        finalBalance1.setText("");

        state2.setText("Has not deposited");
        inBalance2.setText("");
        finalBalance2.setText("");

        state3.setText("Has not deposited");
        inBalance3.setText("");
        finalBalance3.setText("");

        state4.setText("Has not deposited");
        inBalance4.setText("");
        finalBalance4.setText("");

        state5.setText("Has not deposited");
        inBalance5.setText("");
        finalBalance5.setText("");

        state6.setText("Has not withdrawn");
        inBalance6.setText("");
        finalBalance6.setText("");

        state7.setText("Has not withdrawn");
        inBalance7.setText("");
        finalBalance7.setText("");

        state8.setText("Has not withdrawn");
        inBalance8.setText("");
        finalBalance8.setText("");

        state9.setText("Has not withdrawn");
        inBalance9.setText("");
        finalBalance9.setText("");

        state10.setText("Has not withdrawn");
        inBalance10.setText("");
        finalBalance10.setText("");

        lbl.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    class DepositThreadMonitor extends Thread {
        ReaderWriter.AddAPennyTask target;
        Label state;
        Label inBalance;
        Label finalBalance;

        DepositThreadMonitor(ReaderWriter.AddAPennyTask target, Label state, Label inBalance, Label finalBalance) {
            this.target = target;
            this.state = state;
            this.inBalance = inBalance;
            this.finalBalance = finalBalance;
        }

        @Override
        public void run() {
            target.start();
            while (target.getState().toString() != "TERMINATED") {
                if (target.getState().toString() == "TIMED_WAITING") {
                    Platform.runLater(() -> {
                        state.setText("Waiting");
                    });
                }

                if (target.getState().toString() == "RUNNABLE") {
                    Platform.runLater(() -> {
                        state.setText("Depositing");
                    });
                }
            }
            Platform.runLater(() -> {
                state.setText("Has Deposited");
                inBalance.setText("" + target.initialBalance);
                finalBalance.setText("" + target.finalBalance);
                lbl.setText("" + finalBalance.getText());
            });

        }
    }

        class WithdrawThreadMonitor extends Thread {
            ReaderWriter.RemoveAPennyTask target;
            Label state;
            Label inBalance;
            Label finalBalance;

            WithdrawThreadMonitor(ReaderWriter.RemoveAPennyTask target, Label state, Label inBalance, Label finalBalance) {
                this.target = target;
                this.state = state;
                this.inBalance = inBalance;
                this.finalBalance = finalBalance;
            }

            @Override
            public void run() {
                target.start();
                while (target.getState().toString() != "TERMINATED") {
                    if (target.getState().toString() == "TIMED_WAITING") {
                        Platform.runLater(() -> {
                            state.setText("Waiting");
                        });
                    }

                    if (target.getState().toString() == "RUNNABLE") {
                        Platform.runLater(() -> {
                            state.setText("Withdrawing");
                        });
                    }
                }
                Platform.runLater(() -> {
                    state.setText("Has Withdrawn");
                    inBalance.setText("" + target.initialBalance);
                    finalBalance.setText("" + target.finalBalance);
                    lbl.setText("" + finalBalance.getText());
                });
            }
    }
}


