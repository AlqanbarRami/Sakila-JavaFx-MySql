package Movie;


import com.sakila.Film;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ByActor extends FindMovie {


    VBox vBoxForInfo = new VBox(20);
    @Override
    public void findMovie(String nameOfQuery, String url, String user, String pass) {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("SELECT ac.first_name, ac.last_name,fi.film_id, fi.title, fi.release_year \n" +
                    "FROM film as fi\n" +
                    "INNER JOIN actor as ac\n" +
                    "INNER JOIN film_actor as fiac\n" +
                    "ON ac.actor_id = fiac.actor_id AND fi.film_id = fiac.film_id\n" +
                    "WHERE ac.first_name like"+ " '"+'%'+nameOfQuery+'%'+"';");
            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            while (getRes.next()) {
                // Need this object just to save id for every movie//
                Film film = new Film();
                film.setFilmId(getRes.getInt("fi.film_id"));

                HBox hBoxForInfo = new HBox(10);
                Button editButton = new Button("Edit");
                Button deleteButton = new Button("Delete");
                Label actorFullName = new Label("| Actor: "+getRes.getString("ac.first_name") + " " + getRes.getString("ac.last_name") + " |");
                Label filmTitle = new Label("Title:"+ getRes.getString("fi.title")+ " |");
                Label filmReleaseYear = new Label("Release Year: "+getRes.getString("fi.Release_year")+ " |");
                hBoxForInfo.getChildren().addAll(actorFullName,filmTitle,filmReleaseYear,editButton,deleteButton);
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

        vBoxForInfo.setAlignment(Pos.CENTER);
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
