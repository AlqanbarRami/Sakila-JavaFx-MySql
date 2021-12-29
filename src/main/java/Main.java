import Boards.Login;
import Boards.MainBoard;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {
    Login login = new Login();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(login.loginStage());
        primaryStage.show();
        login.loginButton.setOnAction(event -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/"+login.databaseName.getText()+"?serverTimezone=UTC",login.userName.getText(),login.passWord.getText() );
                if (connection.isValid(10)) {
                    MainBoard mainBoard = new MainBoard();
                    mainBoard.setUrl("jdbc:mysql://localhost/"+login.databaseName.getText()+"?serverTimezone=UTC");
                    mainBoard.setUser(login.userName.getText());
                    mainBoard.setPass(login.passWord.getText());
                    primaryStage.close();
                    mainBoard.mainScene();

                }
                else {
                    System.out.println("Error");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
    }


}





