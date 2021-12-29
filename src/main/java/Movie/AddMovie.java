package Movie;

import Boards.MainBoard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;


public class AddMovie extends MainBoard {
    public Label titleLabel = new Label("Title:");
    public Label descriptionLabel = new Label("Description:");
    public Label releaseYear = new Label("Release Year:");
    public Label language_id = new Label("Language id:");
    public TextField titleInput = new TextField();
    public TextField descriptionInput = new TextField();
    public TextField releaseYearInput = new TextField();
    public TextField languageInput = new TextField();
    public Button buttonToInsert = new Button("Add New");
    HBox hBoxForTitle = new HBox(10);
    HBox hBoxForDescription = new HBox(10);
    HBox hBoxForRelease = new HBox(10);
    HBox hBoxForLanguage = new HBox(10);
    VBox vBox = new VBox(40);
    Stage stage = new Stage();

    public void AddNewMovieStage() {

        hBoxForTitle.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(0, 40, 0, 0));
        hBoxForDescription.setAlignment(Pos.CENTER);
        hBoxForLanguage.setAlignment(Pos.CENTER);
        hBoxForRelease.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        buttonToInsert.setCursor(Cursor.HAND);

        hBoxForTitle.getChildren().addAll(titleLabel, titleInput);
        hBoxForDescription.getChildren().addAll(descriptionLabel, descriptionInput);
        hBoxForRelease.getChildren().addAll(releaseYear, releaseYearInput);
        hBoxForLanguage.getChildren().addAll(language_id, languageInput);
        vBox.getChildren().addAll(hBoxForTitle, hBoxForDescription, hBoxForRelease, hBoxForLanguage, buttonToInsert);

        buttonToInsert.setOnAction(event -> AddMovieNow(titleInput.getText(), descriptionInput.getText(), releaseYearInput.getText(), Integer.parseInt(languageInput.getText())));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Add new Movie");
        stage.show();


    }

    public void AddMovieNow(String title, String description, String year, int language) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String recordQuery = ("INSERT INTO film(title, description, release_year, language_id) \n" +
                    "VALUES('"+title+"','"+description+"','"+year+"',"+language+");");
            System.out.println(recordQuery);
            connection.createStatement().executeLargeUpdate(recordQuery);
            stage.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
