package Customer;

import Boards.MainBoard;
import com.sakila.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FindCustomer extends MainBoard {
    public BorderPane findPane = new BorderPane();
    public TableView<String> customerTableView = new TableView();
    public TableColumn<String , String> customerFirstName = new TableColumn<>("First Name");
    public TableColumn<String, String> customerLastName = new TableColumn<>("Last Name");
    public TableColumn<String, String> customerEmail = new TableColumn<>("Email");
    public TableColumn<String, String> customerCountry = new TableColumn<>("Country");
    public TableColumn<String, String > customerCity = new TableColumn<>("City");
    public TableColumn<String, String> customerAddress = new TableColumn<>("Address");


    public Stage foundStage = new Stage();

}
