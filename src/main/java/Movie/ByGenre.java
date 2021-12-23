package Movie;

import com.sakila.Category;
import com.sakila.Film;
import com.sun.org.apache.bcel.internal.classfile.Unknown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ByGenre extends FindMovie {
    public TableColumn<Category,String> genreName = new TableColumn<>("Genre");
    public TableView<Category> genreTableView = new TableView<>();
    public TableView<Film> movieTableView = new TableView<>();
    public ObservableList<Category> genreList;
    public ObservableList stringList;


    @Override
    public void findMovie(String nameOfQuery,String url , String user,String pass) {

        movieList = FXCollections.observableArrayList();
        genreList = FXCollections.observableArrayList();
        stringList = FXCollections.observableArrayList();

        /*
        item.getFilmId(),item.getTitle(),item.getDescription(),item.getReleaseYear(),item.getRentalDuration(),
                        item.getRentalRate(),item.getLength(),item.getReplacementCost(),item.getRating()
         */
        // genreTable.getColumns().add(genreName);
        /*
        movieTableView.getColumns().addAll(movieId,movieTitle, movieDescription, movieReleaseYear,
                movieRentalDuration, movieRentalRate, movieLength, movieReplacementCost,
                movieRating);

         */

        //genreTableView.getColumns().add(genreName);

        movieTableView.getColumns().addAll(movieId, movieTitle, movieDescription, movieReleaseYear,
                movieRentalDuration, movieRentalRate, movieLength, movieReplacementCost,
                movieRating);

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select  ca.name ,fi.title, fi.film_id,  " +
                    " fi.description, fi.release_year,fi.rental_duration, " +
                    "fi.rental_rate, fi.length,fi.replacement_cost,fi.rating\n" +
                    "from sakila.film as fi\n" +
                    "INNER JOIN category as ca\n" +
                    "INNER JOIN film_category as fiac\n" +
                    "on fi.film_id = fiac.film_id AND ca.category_id = fiac.category_id\n" +
                    "Where ca.name like" + " '" + '%' + nameOfQuery + '%' + "';");
            getRes = conn.createStatement().executeQuery(recordQuery);
            HBox hBoxForTable = new HBox();
            hBoxForTable.setPrefWidth(900);
            while (getRes.next()) {
            //    genreList.add(new Category(getRes.getString("name")));
                movieList.add(new Film(
                        getRes.getInt("film_id"),
                        getRes.getString("title"),
                        getRes.getString("description"),
                        getRes.getString("release_year"),
                        getRes.getInt("rental_duration") ,
                        getRes.getDouble("rental_rate"),
                        getRes.getInt("length"),
                        getRes.getDouble("replacement_cost"),
                        getRes.getString("rating")));

            }

            genreName.setCellValueFactory(new PropertyValueFactory<>("name"));
            movieId.setCellValueFactory(new PropertyValueFactory<>("filmId"));
            movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            movieReleaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
            movieRentalDuration.setCellValueFactory(new PropertyValueFactory<>("rentalDuration"));
            movieRentalRate.setCellValueFactory(new PropertyValueFactory<>("rentalRate"));
            movieLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            movieReplacementCost.setCellValueFactory(new PropertyValueFactory<>("replacementCost"));
            movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

            movieTableView.setItems(movieList);
            findPane.setCenter(movieTableView);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        hBoxForMainMenuBack.getChildren().add(backToMainMenu);
        backToMainMenu.setCursor(Cursor.HAND);
        hBoxForMainMenuBack.setPadding(new Insets(20,0,20,5));
        movieTableView.setItems(movieList);
        findPane.setTop(hBoxForMainMenuBack);
        findPane.setCenter(movieTableView);
        Scene findScene = new Scene(findPane, 1200, 800);
        foundStage.setScene(findScene);
        BackHome();
        foundStage.show();

    }

}
