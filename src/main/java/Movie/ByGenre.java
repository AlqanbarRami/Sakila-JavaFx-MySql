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

public class ByGenre extends FindMovie {
    VBox vboxForInfo = new VBox(20);
    @Override
    public void findMovie(String nameOfQuery,String url , String user,String pass) {
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
                      Film film = new Film();
                      film.setFilmId(getRes.getInt("fi.film_id"));
                      HBox hBoxForInfo = new HBox(5);
                      Button editButton = new Button("Edit");
                      Button deleteButton = new Button("Delete");
                      Label Genre = new Label("| Genre: " + getRes.getString("ca.name") + " |");
                      Label Title = new Label(" Title: " +getRes.getString("title")+ " |");
                      Label ReleaseYear = new Label(" Release Year: " +getRes.getString("release_year")+ " |");
                      Label RentalDuration = new Label(" Duration: " + getRes.getInt("rental_duration")+ " |");
                      Label RentalRate = new Label(" Rate: " + getRes.getDouble("rental_rate") + " |");
                      Label Length = new Label(" Length: " + getRes.getInt("length") + " |");
                      Label ReplacementCost = new Label(" Rating: " +getRes.getString("rating")+ " |");
                      hBoxForInfo.getChildren().addAll(Genre,Title,ReleaseYear,RentalDuration,RentalRate,Length,ReplacementCost,editButton,deleteButton);
                      vboxForInfo.getChildren().add(hBoxForInfo);
                      hBoxForInfo.setAlignment(Pos.CENTER);
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
        hBoxForMainMenuBack.getChildren().add(backToMainMenu);
        backToMainMenu.setCursor(Cursor.HAND);
        hBoxForMainMenuBack.setPadding(new Insets(20,0,20,5));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(vboxForInfo);
        findPane.setTop(hBoxForMainMenuBack);
        findPane.setCenter(scrollPane);
        Scene findScene = new Scene(findPane, 1200, 800);
        foundStage.setScene(findScene);
        BackHome();
        foundStage.show();

    }

}
