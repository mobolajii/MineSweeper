package de.unitrier.st.fp.s23.blatt02;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import java.io.*;
import java.util.StringTokenizer;

public class MineSweeperFX extends Application
{
    @FXML BorderPane bp;
    @FXML
    MenuItem File;
    @FXML MenuItem Restart;
    @FXML MenuItem Quit;
    @FXML
    Button restart;
    @FXML MenuItem Load;
    @FXML MenuItem Save;
    private Game game;

    public void start(Stage stage) throws Exception {
        // TODO: Load MineSweeperFX.fxml with the FXMLLoader and set it as root component.

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MineSweeperFX.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        int columns = 15;
        int rows = 20;


        game = new Game(columns, rows);
        MineSweeperPane mineSweeperPane = new MineSweeperPane(game);
        bp.setCenter(mineSweeperPane);

        restart.setOnAction(actionEvent -> {
            mineSweeperPane.setGame(new Game(columns, rows));
        });

        Restart.setOnAction(actionEvent -> {
            mineSweeperPane.setGame(new Game(columns, rows));
        });

        Quit.setOnAction(actionEvent -> {
            stage.close();
        });




        Save.setOnAction(actionEvent -> {
            try {
                FileWriter fileWriter = new FileWriter("game.csv");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                for (int i = 0; i < columns; i++){
                    for (int j = 0; j < rows; j++)
                    {
                        printWriter.print(game.getField(i, j).isBomb()? "M": "F");
                        if (j < rows - 1) {
                            printWriter.write(",");
                        }
                    }
                    printWriter.println();
                }
                bufferedWriter.close();
                fileWriter.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Spiel erfolgreich gespeichert");
                alert.show();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Es ist ein Fehler aufgetreten.");
                alert.show();
                e.printStackTrace();
            }

        });

        Load.setOnAction(actionEvent -> {
            try {
                FileReader fileReader = new FileReader("game.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                int row = 0;

                while ((line = bufferedReader.readLine()) != null && row < rows) {
                    StringTokenizer st = new StringTokenizer(line, ",");

                    for (int columnIndex = 0; columnIndex < columns && st.hasMoreTokens(); columnIndex++) {
                        String value = st.nextToken().trim();

                        if (value.equals("M")) {
                            game.getField(columnIndex, row).setBomb(true);
                        } else if (value.equals("F")) {
                            game.getField(columnIndex, row).setBomb(false);
                        }
                    }

                    row++;
                }

                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




        Scene scene = new Scene(root);
        stage.setTitle(this.getClass().getSimpleName());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
