package Language;

import Boards.MainBoard;
import com.sakila.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindLanguage extends MainBoard {

    public VBox vboxForLanguage = new VBox(30);
    public TableView<Film> moviesView ;
    public TableColumn<Film, String> movieTitle ;
    public TableColumn<Film, String> movieDescription ;
    public TableColumn<Film, String> movieReleaseYear ;
    public TableColumn<Film, Integer> movieLength;
    public TableColumn<Film, String> movieRating ;
    public ObservableList<Film> list;

    public void GetLanguage(String nameOfLanguage, String url, String user, String pass) {
        String recordQuery;
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            if (nameOfLanguage.isEmpty()) {
                recordQuery = "SELECT la.language_id , la.name from language as la";
            } else {
                recordQuery = ("SELECT la.language_id , la.name from language as la where la.name like " + "'" + "%" + nameOfLanguage + "%" + "';");
            }

            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            System.out.println(recordQuery);
            while (getRes.next()) {
                Language language = new Language();
                language.setLanguageId(getRes.getInt("language_id"));
                language.setName(getRes.getString("name"));
                HBox hbxForInfoLabel = new HBox(40);
                Label languageId = new Label("Language ID: " + language.getLanguageId());
                Label languageName = new Label("Film Title: " + language.getName());
                Button getMovies = new Button("Get Movies");
                hbxForInfoLabel.getChildren().addAll(languageId, languageName, getMovies);
                hbxForInfoLabel.setAlignment(Pos.CENTER);
                vboxForLanguage.getChildren().add(hbxForInfoLabel);
                getMovies.setCursor(Cursor.HAND);
                getMovies.setOnAction(event -> {
                    try {
                        GetMoviesByThisLanguage(language.getLanguageId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        vboxForLanguage.setAlignment(Pos.BASELINE_CENTER);
        BorderPane languagePane = new BorderPane();
        languagePane.setCenter(vboxForLanguage);
        Scene languageScene = new Scene(languagePane, 800, 500);
        Stage stageLanguage = new Stage();
        stageLanguage.setTitle("Movies language in Story");
        stageLanguage.setScene(languageScene);
        stageLanguage.show();
    }

    private void GetMoviesByThisLanguage(int languageId) throws SQLException {
        movieTitle = new TableColumn<>("Title");
        movieDescription = new TableColumn<>("Description");
        movieReleaseYear = new TableColumn<>("Release Year");
        movieLength = new TableColumn<>("Length");
        movieRating = new TableColumn<>("Rating");
        list = FXCollections.observableArrayList();
        moviesView = new TableView<>();
        moviesView.getColumns().addAll(movieTitle, movieDescription, movieReleaseYear,
                movieLength, movieRating);

        try {
            Connection conn = DriverManager.getConnection(url,user,pass);
            String recordQuery2 = ("SELECT fi.title , fi.description, fi.release_year, fi.length, fi.rating\n" +
                    "from film as fi\n" +
                    "WHERE fi.language_id =" + languageId +";");
            System.out.println(recordQuery2);
            getRes = conn.createStatement().executeQuery(recordQuery2);
            while (getRes.next()) {
                list.add(new Film(getRes.getString("title"),
                        getRes.getString("description"),
                        getRes.getString("release_year"),
                        getRes.getInt("length"),
                        getRes.getString("rating")));
            }
            movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            movieDescription.setPrefWidth(500);
            movieReleaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
            movieLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
            moviesView.setItems(list);
            BorderPane viewMoviePane = new BorderPane();
            viewMoviePane.setCenter(moviesView);
            Scene viewMovieScene= new Scene(viewMoviePane,1000,500);
            Stage viewMovieStage = new Stage();
            viewMovieStage.setTitle("Movies By Language");
            viewMovieStage.setScene(viewMovieScene);
            viewMovieStage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
