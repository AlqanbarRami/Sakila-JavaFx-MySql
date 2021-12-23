package Movie;

import Boards.Login;
import com.sakila.Category;
import com.sakila.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ByActor extends FindMovie {
    Login login = new Login();
    @Override
    public void findMovie(String nameOfQuery, String url, String user, String pass) {
        movieList = FXCollections.observableArrayList();
        movieTableView.getColumns().addAll(movieId, movieTitle, movieDescription, movieReleaseYear,
                movieRentalDuration, movieRentalRate, movieLength, movieReplacementCost,
                movieRating);
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select fi.film_id,fi.title,fi.description," +
                    "fi.release_year,fi.rental_duration,fi.rental_rate,fi.length,fi.replacement_cost,fi.rating\n" +
                    "from sakila.film as fi\n" +
                    "JOIN actor as ac\n" +
                    "JOIN film_actor as fiac\n" +
                    "on ac.actor_id = fiac.actor_id AND fi.film_id = fiac.film_id\n" +
                    "Where ac.first_name like"+ " '"+'%'+nameOfQuery+'%'+"';");
            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            System.out.println("success");
            HBox hBoxForTable = new HBox();
            hBoxForTable.setPrefWidth(900);
            while (getRes.next()) {
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

            movieId.setCellValueFactory(new PropertyValueFactory<>("filmId"));
            movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            movieDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            movieReleaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
            movieRentalDuration.setCellValueFactory(new PropertyValueFactory<>("rentalDuration"));
            movieRentalRate.setCellValueFactory(new PropertyValueFactory<>("rentalRate"));
            movieLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            movieReplacementCost.setCellValueFactory(new PropertyValueFactory<>("replacementCost"));
            movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));



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

    /*
    @Override
    public Stage findMovie(String nameOfQuery) {
        movieList = FXCollections.observableArrayList();
        movieTableView.getColumns().addAll(movieId, movieTitle, movieDescription, movieReleaseYear,
                movieRentalDuration, movieRentalRate, movieLength, movieReplacementCost,
                movieRating);
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select fi.film_id,fi.title,fi.description," +
                    "fi.release_year,fi.rental_duration,fi.rental_rate,fi.length,fi.replacement_cost,fi.rating\n" +
                    "from sakila.film as fi\n" +
                    "JOIN actor as ac\n" +
                    "JOIN film_actor as fiac\n" +
                    "on ac.actor_id = fiac.actor_id AND fi.film_id = fiac.film_id\n" +
                    "Where ac.first_name like"+ " '"+'%'+nameOfQuery+'%'+"';");
            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            System.out.println("success");
            HBox hBoxForTable = new HBox();
            hBoxForTable.setPrefWidth(900);
            while (getRes.next()) {
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
        Scene findScene = new Scene(findPane,1200,800);
        return findScene;
    }

     */
}
