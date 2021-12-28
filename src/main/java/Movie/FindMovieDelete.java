package Movie;

import com.sakila.Film;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FindMovieDelete extends FindMovie{
    VBox vBoxForInfo = new VBox(20);

    @Override
    public void findMovie(String nameOfQuery, String url, String user, String pass) {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("SELECT film_id,title,release_year,length from film Where title like"+ " '"+'%'+nameOfQuery+'%'+"'");
            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            while (getRes.next()) {
                // Need this object just to save id for every movie//
                Film film = new Film();
                film.setFilmId(getRes.getInt("film_id"));
                HBox hBoxForInfo = new HBox(10);
                Button editButton = new Button("Edit");
                Button deleteButton = new Button("Delete");
                Label filmId = new Label("| Title:"+ getRes.getInt("film_id")+ " |");
                Label filmTitle = new Label("Title:"+ getRes.getString("title")+ " |");
                Label filmReleaseYear = new Label("Release Year: "+getRes.getString("Release_year")+ " |");
                Label filmLength = new Label("Length: "+getRes.getString("length")+ " |");
                hBoxForInfo.getChildren().addAll(filmId,filmTitle,filmReleaseYear,filmLength,editButton,deleteButton);
                vBoxForInfo.getChildren().add(hBoxForInfo);
                hBoxForInfo.setAlignment(Pos.CENTER);
                editButton.setCursor(Cursor.HAND);
                deleteButton.setCursor(Cursor.HAND);
                deleteButton.setOnAction(event -> {
                    DeleteMovie deleteMovie = new DeleteMovie();
                    deleteMovie.MovieDelete(film.getFilmId());

                });
                editButton.setOnAction(event -> {
                    EditMovie editMovie = new EditMovie();
                    editMovie.MovieEdit(film.getFilmId());
                });

            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        vBoxForInfo.setAlignment(Pos.BASELINE_CENTER);
        hBoxForMainMenuBack.getChildren().add(backToMainMenu);
        vBoxForInfo.setPadding(new Insets(50));
        backToMainMenu.setCursor(Cursor.HAND);
        hBoxForMainMenuBack.setPadding(new Insets(20,0,20,5));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(vBoxForInfo);
        findPane.setTop(hBoxForMainMenuBack);
        findPane.setCenter(scrollPane);
        Scene findScene = new Scene(findPane, 1200, 800);
        foundStage.setScene(findScene);
        BackHome();
        foundStage.show();

    }
    }
