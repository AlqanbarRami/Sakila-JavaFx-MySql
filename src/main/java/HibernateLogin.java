import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

public class HibernateLogin {
 // This class just to show you that we did another login with hibernate. We used JDBC in our project.

    Button loginButton = new Button("Login");
    TextField userName = new TextField();
    PasswordField passWord = new PasswordField();
    TextField dbName = new TextField();
    Label Name = new Label("Name");
    Label user = new Label("User");
    Label pass = new Label("Pass");
    HBox hBoxForUserName = new HBox(10);
    HBox hBoxForPassword = new HBox(10);
    HBox hBoxForDb = new HBox(10);
    VBox forEveryThing = new VBox(20);
    BorderPane loginPain = new BorderPane();

    public void showLoginBoard() {
        hBoxForDb.getChildren().addAll(Name, dbName);
        hBoxForUserName.getChildren().addAll(user, userName);
        hBoxForPassword.getChildren().addAll(pass, passWord);
        forEveryThing.getChildren().addAll(hBoxForDb, hBoxForUserName, hBoxForPassword, loginButton);
        forEveryThing.setPadding(new Insets(20));
        forEveryThing.setAlignment(Pos.CENTER);
        loginPain.setCenter(forEveryThing);
        Scene scene = new Scene(loginPain, 300, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loginButton.setOnAction(event -> {
            SessionFactory sessionFactory;
            Configuration configuration = new Configuration();
            Properties mySittings = new Properties();
            mySittings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            mySittings.put(Environment.URL, "jdbc:mysql://localhost/" + dbName.getText() + "?serverTimezone=UTC");
            mySittings.put(Environment.USER, userName.getText());
            mySittings.put(Environment.PASS, passWord.getText());
            mySittings.put(Environment.SHOW_SQL, "true");
            configuration.setProperties(mySittings);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();
            Query rs = session.createNativeQuery("SELECT title FROM film ");
            List<String> list = rs.getResultList();
            try {
                Transaction transaction = session.beginTransaction();
                for (String item : list) {
                    System.out.println(item);
                }
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        });

    }

}






