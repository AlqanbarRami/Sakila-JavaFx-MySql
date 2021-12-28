package Boards;

import Customer.ByFirstName;
import Movie.*;
import Language.FindLanguage;
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
    public ResultSet getRes;
    public Label searchMovie = new Label("Search Movie");
    public TextField searchMovieInput = new TextField();
    public Button searchMovieButton = new Button("Find Movie");
    public HBox movieBox = new HBox(20);

    public Label searchMovieToDelete = new Label("Delete Movie");
    public TextField searchMovieInputDelete = new TextField();
    public Button searchMovieButtonDelete = new Button("Find Movie");
    public HBox movieBoxToDelete = new HBox(20);

    public Label searchActor = new Label("Search Actor");
    public TextField searchActorInput = new TextField();
    public Button searchActorButton = new Button("Find Actor");
    public HBox actorBox = new HBox(20);

    public Label searchGenre = new Label("Search Genre");
    public TextField searchGenreInput = new TextField();
    public Button searchGenreButton = new Button("Find Genre");
    public HBox genreBox = new HBox(20);

    public Label searchCustomer = new Label("Search Customer");
    public TextField searchCustomerInput = new TextField();
    public Button searchCustomerButton = new Button("Find Customer");
    public HBox hBoxForLanguage = new HBox(20);

    public Label searchLanguage = new Label("Search Language");
    public TextField searchLanguageInput = new TextField();
    public Button searchLanguageButton = new Button("Find Language");

    public Stage HomeStage = new Stage();
    public HBox customerBox = new HBox(20);
    public VBox vBoxForFindingMovie =new VBox(30);
    public  VBox vBoxForRight = new VBox(30);
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
        searchLanguageInput.setPromptText("Ex: English");
        searchMovieInputDelete.setPromptText("Ex: Academy");


        movieBox.getChildren().addAll(searchMovie,searchMovieInput,searchMovieButton);
        actorBox.getChildren().addAll(searchActor,searchActorInput,searchActorButton);
        genreBox.getChildren().addAll(searchGenre,searchGenreInput,searchGenreButton);
        customerBox.getChildren().addAll(searchCustomer,searchCustomerInput,searchCustomerButton);
        hBoxForLanguage.getChildren().addAll(searchLanguage,searchLanguageInput, searchLanguageButton);
        movieBoxToDelete.getChildren().addAll(searchMovieToDelete,searchMovieInputDelete,searchMovieButtonDelete);

        searchMovieButton.setCursor(Cursor.HAND);
        searchActorButton.setCursor(Cursor.HAND);
        searchGenreButton.setCursor(Cursor.HAND);
        searchCustomerButton.setCursor(Cursor.HAND);
        searchLanguageButton.setCursor(Cursor.HAND);
        searchMovieButtonDelete.setCursor(Cursor.HAND);

        vBoxForRight.getChildren().addAll(customerBox,hBoxForLanguage,movieBoxToDelete);
        vBoxForFindingMovie.getChildren().addAll(movieBox,actorBox,genreBox);


        movieBox.setAlignment(Pos.CENTER);
        actorBox.setAlignment(Pos.CENTER);
        genreBox.setAlignment(Pos.CENTER);
        customerBox.setAlignment(Pos.CENTER);
        hBoxForLanguage.setAlignment(Pos.CENTER);
        movieBoxToDelete.setAlignment(Pos.CENTER);

        mainBorder.setPadding(new Insets(40));
        mainBorder.setLeft(vBoxForFindingMovie);
        mainBorder.setRight(vBoxForRight);
        Scene mainScene = new Scene(mainBorder,1000,400);
        HomeStage.setScene(mainScene);

        searchMovieButton.setOnAction(event ->{
            ByName byName = new ByName();
            byName.findMovie(searchMovieInput.getText(),getUrl(),getUser(),getPass());
            HomeStage.close();
        });

        searchMovieButtonDelete.setOnAction(event -> {
            FindMovieDelete findMovieDelete = new FindMovieDelete();
            findMovieDelete.findMovie(searchMovieInputDelete.getText(),getUrl(),getUser(),getPass());
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
            findCustomer.findCustomer(searchCustomerInput.getText(),getUrl(),getUser(),getPass());
        });

        searchLanguageButton.setOnAction(event -> {
            FindLanguage findLanguage = new FindLanguage();
            findLanguage.GetLanguage(searchLanguageInput.getText(),getUrl(),getUser(),getPass());
        });



        HomeStage.setTitle("CD Shopping");
        HomeStage.show();
    }


}
