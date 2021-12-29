package Movie;

import Boards.MainBoard;
import com.sakila.Film;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public abstract class FindMovie extends MainBoard {
    public BorderPane findPane = new BorderPane();
    public TableView movieTableView = new TableView<>();
    public TableColumn<Film, Integer> movieId = new TableColumn<>("Id");
    public TableColumn<Film, String> movieTitle = new TableColumn<>("Title");
    public TableColumn<Film, String> movieDescription = new TableColumn<>("Description");
    public TableColumn<Film, String> movieReleaseYear = new TableColumn<>("Release Year");
    public TableColumn<Film, Integer> movieRentalDuration = new TableColumn<>("Rental Duration");
    public TableColumn<Film, Double> movieRentalRate = new TableColumn<>("Rental Rate");
    public TableColumn<Film, Integer> movieLength = new TableColumn<>("Length");
    public TableColumn<Film, Double> movieReplacementCost = new TableColumn<>("Replacement Cost");
    public TableColumn<Film, String> movieRating = new TableColumn<>("Rating");
    public Button backToMainMenu = new Button("<< Back To Home");
    public HBox hBoxForMainMenuBack = new HBox();
    public ObservableList<Film> movieList;
    public Stage foundStage = new Stage();


    public abstract void findMovie(String nameOfQuery,String url,String user, String pass);

    public void BackHome(){
        backToMainMenu.setOnAction(event->{
            mainScene();
            foundStage.close();
        });
    }
}
