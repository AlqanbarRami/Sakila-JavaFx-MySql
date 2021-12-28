package Boards;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login {
    public Button loginButton = new Button("Login");
    public TextField userName = new TextField();
    public PasswordField passWord = new PasswordField();
    public TextField databaseName = new TextField();
    Label user = new Label("User");
    Label pass = new Label("Pass");
    Label DbName = new Label("Name");
    HBox hBoxForUserName = new HBox(30);
    HBox hBoxForPassword = new HBox(30);
    HBox hBoxForUrl = new HBox(30);
    VBox forEveryThing = new VBox(20);
    BorderPane loginPain = new BorderPane();

    public Scene loginStage(){
        hBoxForUserName.setAlignment(Pos.CENTER);
        hBoxForPassword.setAlignment(Pos.CENTER);
        hBoxForUrl.setAlignment(Pos.CENTER);
        hBoxForUserName.setPrefWidth(250);
        hBoxForPassword.setPrefWidth(250);
        hBoxForUrl.setPrefWidth(250);
        loginButton.setCursor(Cursor.HAND);
        forEveryThing.setMaxWidth(300);
        hBoxForUserName.getChildren().addAll(user,userName);
        hBoxForPassword.getChildren().addAll(pass,passWord);
        hBoxForUrl.getChildren().addAll(DbName,databaseName);
        forEveryThing.getChildren().addAll(hBoxForUrl, hBoxForUserName,hBoxForPassword,loginButton);
        forEveryThing.setAlignment(Pos.CENTER);
        loginPain.setCenter(forEveryThing);
        return new Scene(loginPain, 400,400);

    }

}
