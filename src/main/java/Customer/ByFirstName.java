package Customer;

import Boards.Login;
import com.sakila.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class ByFirstName extends FindCustomer{
    Login login = new Login();
    public Customer customer = new Customer();
    public Country country = new Country();
    public City city = new City();
    public Address address = new Address();
    public ByFirstName(){

    }
    public ByFirstName(Customer cu, Country co , City ci , Address add){
        this.customer = cu;
        this.country = co;
        this.city = ci;
        this.address =add;
    }

    public void findCustomer( String nameOfQuery) {
        ObservableList mainList = FXCollections.observableArrayList();

                 try {
                     Connection conn = DriverManager.getConnection(url, user, pass);
                     String recordQuery = ("Select cu.first_name,cu.last_name, cu.email, co.country, ci.city , ad.address\n" +
                             "                    from customer as cu\n" +
                             "                    INNER JOIN address as ad\n" +
                             "                    INNER JOIN country as co\n" +
                             "                    INNER JOIn city as ci\n" +
                             "on cu.address_id = ad.address_id AND ad.city_id = ci.city_id AND ci.country_id= co.country_id \n" +
                             "Where cu.first_name like"+ " '"+'%'+nameOfQuery+'%'+"';");
                     System.out.println(recordQuery);
                     getRes = conn.createStatement().executeQuery(recordQuery);
                     System.out.println("success");
                     while (getRes.next()) {
                         Customer customer = new Customer();
                         customer.setFirstName(getRes.getString("first_name"));
                         customer.setLastName(getRes.getString("last_name"));
                         customer.setEmail(getRes.getString("email"));
                         Country country = new Country();
                         country.setCountry(getRes.getString("country"));
                         City city = new City();
                         city.setCity(getRes.getString("city"));
                         Address address = new Address();
                         address.setAddress("address");

                                 mainList.addAll(customer);



                     }
                     customerFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                     customerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                     customerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                     customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
                     customerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
                     customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                     customerTableView.setItems(mainList);

                     customerTableView.getColumns().addAll(customerFirstName,customerLastName,customerEmail,customerCountry,customerCity,customerAddress);







                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
                 findPane.setCenter(customerTableView);
                 Scene findScene = new Scene(findPane, 1200, 800);
                 foundStage.setScene(findScene);
                 foundStage.show();
             }



}
