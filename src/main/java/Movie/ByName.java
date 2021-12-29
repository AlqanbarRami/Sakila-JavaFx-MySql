package Movie;


import com.sakila.Film;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ByName extends FindMovie {



    @Override
    public void findMovie(String nameOfQuery,String url, String user, String pass) {
        movieList = FXCollections.observableArrayList();
        movieTableView.getColumns().addAll(movieId, movieTitle, movieDescription, movieReleaseYear,
                movieRentalDuration, movieRentalRate, movieLength, movieReplacementCost,
                movieRating);
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("SELECT film_id,title,description,release_year,rental_duration,rental_rate,length,replacement_cost,rating \n" +
                    "From sakila.film\n" +
                    "Where film.title like"+ " '"+'%'+nameOfQuery+'%'+"';");
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
        Scene findScene = new Scene(findPane,1200,800);
        foundStage.setTitle("Movie By Title Name : " + nameOfQuery);
        foundStage.setScene(findScene);
        BackHome();
        foundStage.show();

    }


}
