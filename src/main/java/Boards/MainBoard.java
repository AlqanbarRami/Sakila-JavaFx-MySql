package Boards;

import Customer.ByFirstName;
import Customer.FindCustomer;
import Movie.ByActor;
import Movie.ByGenre;
import Movie.ByName;
import com.sakila.Film;
import javafx.collections.ObservableList;
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

import java.sql.ResultSet;


public class MainBoard extends Login {
    public static String url ;
    public static String user ;
    public static String pass ;
    public ObservableList<Film> movieList;
    public ResultSet getRes;
    public Label searchMovie = new Label("Search Movie");
    public TextField searchMovieInput = new TextField();
    public Button searchMovieButton = new Button("Find Movie");
    public HBox movieBox = new HBox(20);

    public Label searchActor = new Label("Search Actor");
    public TextField searchActorInput = new TextField();
    public Button searchActorButton = new Button("Find Actor");
    public HBox actorBox = new HBox(20);

    public Label searchGenre = new Label("Search Genre");
    public TextField searchGenreInput = new TextField();
    public Button searchGenreButton = new Button("Find Genre");
    public HBox genreBox = new HBox(20);
    public Stage HomeStage = new Stage();
    public Label searchCustomer = new Label("Search Customer");
    public TextField searchCustomerInput = new TextField();
    public Button searchCustomerButton = new Button("Find Customer");
    public HBox customerBox = new HBox(20);
    public VBox vBoxForFindingMovie =new VBox(30);
    public BorderPane mainBorder = new BorderPane();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void mainScene(){
        searchMovieInput.setPromptText("By Title");
        searchActorInput.setPromptText("By First name Ex:FRED");
        searchGenreInput.setPromptText("Ex Horror");
        searchCustomerInput.setPromptText("By First Name");
        movieBox.getChildren().addAll(searchMovie,searchMovieInput,searchMovieButton);
        actorBox.getChildren().addAll(searchActor,searchActorInput,searchActorButton);
        genreBox.getChildren().addAll(searchGenre,searchGenreInput,searchGenreButton);
        customerBox.getChildren().addAll(searchCustomer,searchCustomerInput,searchCustomerButton);
        searchMovieButton.setCursor(Cursor.HAND);
        searchActorButton.setCursor(Cursor.HAND);
        searchGenreButton.setCursor(Cursor.HAND);
        searchCustomerButton.setCursor(Cursor.HAND);
        vBoxForFindingMovie.getChildren().addAll(movieBox,actorBox,genreBox);
        movieBox.setAlignment(Pos.CENTER);
        actorBox.setAlignment(Pos.CENTER);
        genreBox.setAlignment(Pos.CENTER);
        mainBorder.setPadding(new Insets(40));
        mainBorder.setLeft(vBoxForFindingMovie);
        mainBorder.setRight(customerBox);
        Scene mainScene = new Scene(mainBorder,1200,800);
        HomeStage.setScene(mainScene);
        searchMovieButton.setOnAction(event ->{
            ByName byName = new ByName();
            byName.findMovie(searchMovieInput.getText(),getUrl(),getUser(),getPass());
            HomeStage.close();
        });
        searchGenreButton.setOnAction(event ->{
            ByGenre byGenre = new ByGenre();
            byGenre.findMovie(searchGenreInput.getText(),getUrl(),getUser(),getPass());
            HomeStage.close();
        });
        searchActorButton.setOnAction(event -> {
            ByActor byActor = new ByActor();
            byActor.findMovie(searchActorInput.getText(),getUrl(),getUser(),getPass());
            HomeStage.close();
        });
        searchCustomerButton.setOnAction(event -> {
            ByFirstName findCustomer = new ByFirstName();
          //  findCustomer.findCustomer(searchCustomerInput.getText(),getUrl(),getUser(),getPass());
        });
        HomeStage.show();
    }


}
