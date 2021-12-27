package Customer;

import com.sakila.*;
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

import java.sql.*;

public class ByFirstName extends CustomerHistory {
    ResultSet getRes;
    public BorderPane findPane = new BorderPane();
    public Stage foundStage = new Stage();
    VBox vBoxForInfo = new VBox(20);
    TextField customerIdField;
    TextField firstNameField ;
    TextField lastNameField ;
    TextField emailField ;
    TextField countryField ;
    TextField cityField;
    TextField addressField ;
    Stage editStage = new Stage();
    Scene findScene;


    public ByFirstName() {

    }

    public void findCustomer(String nameOfQuery, String url, String user, String pass) {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select cu.customer_id, cu.first_name,cu.last_name, cu.email, co.country, ci.city , ad.address\n" +
                    "                    from customer as cu\n" +
                    "                    INNER JOIN address as ad\n" +
                    "                    INNER JOIN country as co\n" +
                    "                    INNER JOIn city as ci\n" +
                    "on cu.address_id = ad.address_id AND ad.city_id = ci.city_id AND ci.country_id= co.country_id \n" +
                    "Where cu.first_name like" + " '" + '%' + nameOfQuery + '%' + "';");
            System.out.println(recordQuery);
            getRes = conn.createStatement().executeQuery(recordQuery);
            while (getRes.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(getRes.getInt("customer_id"));
                customer.setFirstName(getRes.getString("first_name"));
                customer.setLastName(getRes.getString("last_name"));
                customer.setEmail(getRes.getString("email"));
                Country country = new Country();
                country.setCountry(getRes.getString("country"));
                City city = new City();
                city.setCity(getRes.getString("city"));
                Address address = new Address();
                address.setAddress(getRes.getString("address"));
                HBox hbxForInfoLabel = new HBox(10);
                Label firstNameLabel = new Label("First Name:");
                Label firstNameValue = new Label(customer.getFirstName());
                Label lastNameLabel = new Label("Last Name:");
                Label lastNameValue = new Label(customer.getLastName());
                Label emailLabel = new Label("Email:");
                Label emailValue = new Label(customer.getEmail());
                Label countryLabel = new Label("Country:");
                Label countryValue = new Label(country.getCountry());
                Label cityLabel = new Label("City:");
                Label cityValue = new Label(city.getCity());
                Label addressLabel = new Label("Address:");
                Label addressValue = new Label(address.getAddress());
                Button editButton = new Button("Edit");
                Button rentalHistoryButton = new Button("Rental History");
                editButton.setCursor(Cursor.HAND);
                rentalHistoryButton.setCursor(Cursor.HAND);
                hbxForInfoLabel.getChildren().addAll(firstNameLabel, firstNameValue, lastNameLabel
                        , lastNameValue, emailLabel, emailValue, countryLabel, countryValue, cityLabel, cityValue, addressLabel, addressValue, editButton, rentalHistoryButton);

                rentalHistoryButton.setOnAction(event -> {
                            try {
                                GetRentalHistory(customer.getCustomerId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                );

                editButton.setOnAction(event -> {
                    customerIdField = new TextField(String.valueOf(customer.getCustomerId()));
                    customerIdField.setDisable(true);
                    firstNameField = new TextField(firstNameValue.getText());
                    lastNameField = new TextField(lastNameValue.getText());
                    emailField = new TextField(emailValue.getText());
                    countryField = new TextField(countryValue.getText());
                    cityField = new TextField(cityValue.getText());
                    addressField = new TextField(addressValue.getText());
                    BorderPane editPane = new BorderPane();
                    VBox vBoxForTextField = new VBox(30);
                    vBoxForTextField.setMaxWidth(300);
                    vBoxForTextField.setAlignment(Pos.CENTER);
                    Button saveButton = new Button("Save");
                    saveButton.setCursor(Cursor.HAND);
                    saveButton.setOnAction(event2 -> {
                        try {
                            updateCustomerInfo(Integer.parseInt(customerIdField.getText()));

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    vBoxForTextField.getChildren().addAll(customerIdField, firstNameField, lastNameField, emailField, countryField, cityField, addressField, saveButton);
                    editPane.setCenter(vBoxForTextField);
                    Scene editScene = new Scene(editPane, 800, 500);
                    editStage.setScene(editScene);
                    editStage.setTitle("Edit Customer");
                    editStage.show();
                });

                hbxForInfoLabel.setAlignment(Pos.CENTER);
                vBoxForInfo.getChildren().add(hbxForInfoLabel);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        vBoxForInfo.setPadding(new Insets(40));
        vBoxForInfo.setAlignment(Pos.BASELINE_CENTER);
        findPane.setCenter(vBoxForInfo);
        findScene = new Scene(findPane, 1400, 800);
        foundStage.setScene(findScene);
        foundStage.show();
    }
    private void updateCustomerInfo(int id) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String query = "Update customer,country,city,address " +
                    "SET customer.first_name="+"'"+firstNameField.getText()+"'"+
                    ",customer.last_name="+"'"+lastNameField.getText()+"'"+
                    ",customer.email="+"'"+emailField.getText()+"'"+
                    ",country.country="+"'"+countryField.getText()+"'"+
                    ",city.city="+"'"+cityField.getText()+"'"+
                    ",address.address="+"'"+addressField.getText()+"'"+
                    "where customer.customer_id="+id+" AND customer.address_id = address.address_id\n" +
                    "AND address.city_id = city.city_id AND city.country_id = country.country_id";
            System.out.println(query);
            statement.executeUpdate(query);
            editStage.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
