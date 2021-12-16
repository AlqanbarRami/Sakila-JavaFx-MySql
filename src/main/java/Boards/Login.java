package Boards;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Login {
    Button loginButton = new Button("Login");
    TextField userName = new TextField();
    TextField passWord = new TextField();
    Label user = new Label("User");
    Label pass = new Label("Pass");
    HBox hBoxForUserName = new HBox(30);
    HBox hBoxForPassword = new HBox(30);
    VBox forEveryThing = new VBox(20);
    BorderPane loginPain = new BorderPane();

    public Scene loginScene(){
        hBoxForUserName.setAlignment(Pos.CENTER_LEFT);
        hBoxForPassword.setAlignment(Pos.CENTER_LEFT);
        hBoxForUserName.setPrefWidth(250);
        hBoxForPassword.setPrefWidth(250);
        loginButton.setCursor(Cursor.HAND);
        forEveryThing.setMaxWidth(250);
        hBoxForUserName.getChildren().addAll(user,userName);
        hBoxForPassword.getChildren().addAll(pass,passWord);
        forEveryThing.getChildren().addAll(hBoxForUserName,hBoxForPassword,loginButton);
        forEveryThing.setAlignment(Pos.CENTER_RIGHT);
        loginPain.setCenter(forEveryThing);
        Scene sceneLogin = new Scene(loginPain, 1200,800);
        return sceneLogin;
    }

}
