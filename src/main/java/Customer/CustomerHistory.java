package Customer;

import Boards.MainBoard;
import com.sakila.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class CustomerHistory extends MainBoard {
    Stage rentalHistoryStage = new Stage();
    BorderPane rentalHistoryPane = new BorderPane();
    VBox vbForHistoryInfo = new VBox(20);

    public void GetRentalHistory(int id) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select cus.first_name,cus.last_name, fi.title, ren.rental_date, ren.return_date, sta.first_name, sta.last_name\n" +
                    "                    from customer as cus\n" +
                    "                    INNER JOIN rental as ren\n" +
                    "                    INNER JOIN film as fi\n" +
                    "                    INNER JOIN inventory as inv\n" +
                    "                    INNER JOIN staff as sta\n" +
                    "on cus.customer_id = ren.customer_id \n" +
                    "AND ren.inventory_id = inv.inventory_id\n" +
                    "AND ren.staff_id = sta.staff_id\n" +
                    "AND inv.film_id = fi.film_id\n" +
                    "Where cus.customer_id=" + id);
            getRes = conn.createStatement().executeQuery(recordQuery);
            System.out.println(recordQuery);
            while (getRes.next()) {
                Customer customer = new Customer();
                customer.setFirstName(getRes.getString("first_name"));
                customer.setLastName(getRes.getString("last_name"));
                Film film = new Film();
                film.setTitle(getRes.getString("title"));
                Rental rental = new Rental();
                rental.setRentalDate(getRes.getString("rental_date"));
                rental.setReturnDate(getRes.getString("return_date"));
                Staff staff = new Staff();
                staff.setFirstName(getRes.getString("sta.first_name"));
                staff.setLastName(getRes.getString("sta.last_name"));
                HBox hbxForInfoLabel = new HBox(10);
                Label fullName = new Label("Full name: " + customer.getFirstName() + " " + customer.getLastName());
                Label filmTitle = new Label("Film Title: " + film.getTitle());
                Label rentalDate = new Label("Rental Date: " + rental.getRentalDate());
                Label returnDate = new Label("Return Date: " + rental.getReturnDate());
                Label staffName = new Label("Staff Name: " + staff.getFirstName() + " " + staff.getLastName());
                hbxForInfoLabel.getChildren().addAll(fullName, filmTitle, rentalDate
                        , returnDate, staffName);
                hbxForInfoLabel.setAlignment(Pos.CENTER);
                vbForHistoryInfo.getChildren().add(hbxForInfoLabel);
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        vbForHistoryInfo.setAlignment(Pos.BASELINE_CENTER);
        Button paymentHistoryButton = new Button("Payment History");
        paymentHistoryButton.setCursor(Cursor.HAND);
        vbForHistoryInfo.getChildren().add(paymentHistoryButton);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(vbForHistoryInfo);
        rentalHistoryPane.setCenter(scrollPane);
        vbForHistoryInfo.setPadding(new Insets(50));
        Scene rentalHistoryScene = new Scene(rentalHistoryPane,800,500);
        rentalHistoryStage.setScene(rentalHistoryScene);
        rentalHistoryStage.setTitle("Customer Rental History");
        rentalHistoryStage.show();

        paymentHistoryButton.setOnAction(event -> {
            rentalHistoryStage.close();
            GetPaymentHistory(id);
        });


    }

    public void GetPaymentHistory(int id) {
            VBox vBoxForPaymentInfo = new VBox(10);
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String recordQuery = ("Select pay.payment_id, cus.first_name,cus.last_name, fi.title, pay.amount, pay.payment_date, sta.first_name, sta.last_name\n" +
                    "                    from payment as pay\n" +
                    "                    INNER JOIN customer as cus\n" +
                    "                    INNER JOIN rental as ren\n" +
                    "                    INNER JOIN film as fi\n" +
                    "                    INNER JOIN inventory as inv\n" +
                    "                    INNER JOIN staff as sta\n" +
                    "on pay.customer_id = cus.customer_id \n" +
                    "AND pay.rental_id = ren.rental_id\n" +
                    "AND ren.inventory_id = inv.inventory_id\n" +
                    "AND inv.film_id = fi.film_id\n" +
                    "AND pay.staff_id = sta.staff_id\n" +
                    "Where cus.customer_id = "+id);
            getRes = conn.createStatement().executeQuery(recordQuery);
            System.out.println(recordQuery);
            while (getRes.next()) {
                Customer customer = new Customer();
                customer.setFirstName(getRes.getString("first_name"));
                customer.setLastName(getRes.getString("last_name"));
                Film film = new Film();
                film.setTitle(getRes.getString("title"));
                Payment payment = new Payment();
                payment.setAmount(getRes.getDouble("amount"));
                payment.setPaymentDate(getRes.getString("payment_date"));
                Staff staff = new Staff();
                staff.setFirstName(getRes.getString("sta.first_name"));
                staff.setLastName(getRes.getString("sta.last_name"));
                HBox hbxForInfoLabel = new HBox(10);
                Label fullName = new Label("Full name: " + customer.getFirstName() + " " + customer.getLastName());
                Label filmTitle = new Label("Film Title: " + film.getTitle());
                Label amount = new Label("Amount: " + payment.getAmount());
                Label paymentDate = new Label("Payment Date: " + payment.getPaymentDate());
                Label staffName = new Label("Staff Name: " + staff.getFirstName() + " " + staff.getLastName());
                hbxForInfoLabel.getChildren().addAll(fullName, filmTitle, amount
                        , paymentDate, staffName);
                hbxForInfoLabel.setAlignment(Pos.CENTER);
                vBoxForPaymentInfo.getChildren().add(hbxForInfoLabel);
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        vBoxForPaymentInfo.setAlignment(Pos.BASELINE_CENTER);
        ScrollPane scrollPanePayment = new ScrollPane();
        scrollPanePayment.setFitToWidth(true);
        scrollPanePayment.setFitToHeight(true);
        scrollPanePayment.setContent(vBoxForPaymentInfo);
        BorderPane paymentPaneHistory = new BorderPane();
        paymentPaneHistory.setCenter(scrollPanePayment);
        vBoxForPaymentInfo.setPadding(new Insets(50));
        Scene paymentScene = new Scene(paymentPaneHistory,800,500);
        Stage paymentStage = new Stage();
        paymentStage.setTitle("Payment History");
        paymentStage.setScene(paymentScene);
        paymentStage.show();

    }
}
