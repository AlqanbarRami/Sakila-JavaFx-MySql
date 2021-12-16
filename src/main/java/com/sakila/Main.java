package com.sakila;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.persistence.*;
import java.util.List;

public class Main extends Application {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    final ObservableList singer = FXCollections.observableArrayList();
    final ObservableList song = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ComboBox comboBox = new ComboBox(singer);
        comboBox.setPromptText("name");
        Button button = new Button("Show");
        VBox vBoxForCombo = new VBox(20);
        vBoxForCombo.getChildren().addAll(comboBox,button);
        BorderPane mainBorderPane = new BorderPane();
        VBox vBoxForSinger = new VBox(20);
        Button addButton = new Button("Add New Singer");
        TextField singerName = new TextField();
        singerName.setPromptText("Singer name Ex:Rami");
        vBoxForSinger.setPrefWidth(250);
        singerName.setMaxWidth(vBoxForSinger.getPrefWidth());
        vBoxForSinger.getChildren().addAll(singerName,addButton);
        vBoxForSinger.setAlignment(Pos.CENTER);
        vBoxForCombo.setAlignment(Pos.CENTER);
        mainBorderPane.setCenter(vBoxForSinger);
        mainBorderPane.setTop(vBoxForCombo);
        addButton.setOnAction(event -> {
            addButton(singerName);
        });
        button.setOnAction(event ->{
            show();
        });
        Scene scene = new Scene(mainBorderPane, 800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void show() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query =  entityManager.createNativeQuery("SELECT singerName FROM singer WHERE singerName LIKE 'Ram%' ");
            List<String> nameSinger = query.getResultList();
            for(String item : nameSinger){
                singer.add(item);
            }
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    private void addButton(TextField singerName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            Singer singer = new Singer();
            singer.setSingerName(singerName.getText());
            entityManager.persist(singer);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

    }
}
