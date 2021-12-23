import Boards.Login;
import Boards.MainBoard;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Login login = new Login();
        login.loginStage().show();
        login.loginButton.setOnAction(event -> {
            try {

                login.userName.getText();
                String passWord = login.passWord.getText();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/"+login.databaseName.getText()+"?serverTimezone=UTC",login.userName.getText(),login.passWord.getText() );
                if (connection.isValid(10)) {
                    MainBoard mainBoard = new MainBoard();
                    mainBoard.setUrl("jdbc:mysql://localhost/"+login.databaseName.getText()+"?serverTimezone=UTC");
                    mainBoard.setUser(login.userName.getText());
                    mainBoard.setPass(login.passWord.getText());
                    mainBoard.mainScene();
                }
                else {
                    System.out.println("Error");
                }
            }catch (Exception e){
                System.out.println(e);
            }
        });
    }
}





