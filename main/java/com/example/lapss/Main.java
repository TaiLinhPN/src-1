package com.example.lapss;

import com.example.lapss.connect.DBConn;
import com.example.lapss.objects.Laptop;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//import java.awt.Label;
import java.io.IOException;
import java.util.List;

//import jdk.internal.icu.impl.CharacterIteratorWrapper;

//
//public class Main extends Application {
//    @Override
////    public void start(Stage stage) throws IOException {
////        new DBConn();
////        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
////        stage.setTitle("Hello!");
////        stage.setScene(scene);
////        stage.show();
////
////    }
//    public void start(Stage primaryStage) throws Exception {
//
//        DBConn conn = new DBConn();
//        conn.getCompanys();
////        conn.querryDB("DELETE  FROM `companys` WHERE id = "+2);
//
//        HandleLaptop lap  = new HandleLaptop();
//
//        lap.getLaps();
//        lap.updateLaptop("aces","anh2","aces", 22222, 1);

//        lap.createLaps("dell2221", "adasasdasd", "dell", 21111);


//       FlowPane root = new FlowPane();
//       root.setPadding(new Insets(15,15,15,15));
//
//        // Button 1
//        Button button1= new Button("Button1");
//        root.getChildren().add(button1);
//
//
//        // Button 2
//        Button button2 = new Button("Button2");
//        button2.setPrefSize(100, 100);
//        root.getChildren().add(button2);
//
//
//
//
//       Scene scene = new Scene(root, 550, 250);
//
//       primaryStage.setTitle("Laptop Store");
//       primaryStage.setScene(scene);
//       primaryStage.show();
//    }
//
//    void aaaa(){
//      }


public class Main extends Application {



    TableView<Laptop> table;
    int toggle = 0;
    boolean toggleMethod = false;


    TextField tfName = new TextField();
    TextField tfImg = new TextField();
    TextField tfPrice = new TextField();
    TextField tfCompany = new TextField();
    DBConn connection = new DBConn();
    VBox emptyStage = new VBox();
    VBox inputLaptop = new VBox();


    @Override
    public void start(Stage stage) throws IOException {

        VBox root = new VBox();
        VBox laptopRoot = new VBox();
        VBox metherdBox = new VBox();


        HBox headerBox = new HBox();
        Label nameHead = new Label("Name" );
        Label imageHead = new Label("Image" );
        Label priceHead = new Label("Price" );
        Label companyNameHead = new Label("Company" );

        headerBox.getChildren().addAll(nameHead,imageHead,priceHead,companyNameHead);
        headerBox.setSpacing(42);



        Button btnAdd = new Button("Add product");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("aaaaaaa");

                String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ tfName.getText()+"','"+ tfImg.getText()+"',"+ tfPrice.getText()+",'" + tfCompany.getText()+"')";
                connection.querryDB(sql);

                renderLaps(laptopRoot, connection);
            }
        });

        Button btnStopSearch = new Button("Stop search");
        btnStopSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                root.getChildren().remove(btnStopSearch);
                renderLaps(laptopRoot, connection);

            }
        });

        Button btnSearch = new Button("Search product");
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String proviso = tfName.getText();
                root.getChildren().addAll(btnStopSearch);
                renderLaps(laptopRoot, connection, proviso);
            }
        });

        Button btnOpenMetherdBox = new Button("Open Metherd Box");
        btnOpenMetherdBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(toggleMethod){
                    toggleMethod = false ;
                    btnOpenMetherdBox.setText("Open Metherd Box");
                    metherdBox.getChildren().removeAll(btnSearch,btnAdd,inputLaptop);


                    emptyStage.getChildren().remove(metherdBox);
                } else {
                    toggleMethod = true;
                    btnOpenMetherdBox.setText("Close");
                    metherdBox.getChildren().addAll(btnSearch,btnAdd,inputLaptop);


                    emptyStage.getChildren().addAll(metherdBox);

                }
            }
        });

//
//        TableColumn<Laptop, Integer> idColumn = new TableColumn<>("ID");
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn<Laptop, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn<Laptop, String> emailColumn = new TableColumn<>("Image");
//        emailColumn.setCellValueFactory(new PropertyValueFactory<>("img"));

//        TableColumn<Laptop, Float> priceColumn = new TableColumn<>("Price");
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

//        TableColumn<Laptop, String> companyColumn = new TableColumn<>("Company");
//        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

//        table = new TableView<>();
//        table.getColumns().addAll(idColumn,nameColumn,emailColumn);
//
//        table.setItems(getLaptos());

//        public ObservableList<Laptop> getLaptos(){
//            ObservableList<Laptop> laps = FXCollections.observableArrayList();
//            laps.add(new Laptop(1,222,"dell2","https://anphat.com.vn/media/product/38186_laptop_dell_latitude_3420_42lt342001_111.png","q"));
//            laps.add(new Laptop(2,222,"dell3","https://www.laptopvip.vn/images/companies/1/Laptop-Dell-1.jpg?1636257311235","q"));
//            laps.add(new Laptop(3,333,"dell5","https://anphat.com.vn/media/product/38186_laptop_dell_latitude_3420_42lt342001_111.png","q"));
//
//            return laps;
//        }

        inputLaptop.getChildren().addAll(tfName,tfImg,tfPrice,tfCompany);

        root.getChildren().addAll(btnOpenMetherdBox,emptyStage,headerBox, laptopRoot);
        renderLaps(laptopRoot,  connection);


        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Laptop Store");
        stage.setScene(scene);
        stage.show();
    }



    void beforeUpdate(int id){
        Laptop a = connection.getLaps(id);

        tfName.setText(a.getName());
        tfImg.setText(a.getImg());
        tfPrice.setText(String.valueOf(a.getPrice()));
        tfCompany.setText(a.getCompany());
    }
    void update(int id){
        String sql = "UPDATE `laptops` SET `name`='"+ tfName.getText()+"',`price`="+ tfPrice.getText()+",`img`='"+ tfImg.getText()+"',`company`='"+ tfCompany.getText()+"' WHERE id ="+id;
        System.out.println(sql);
        connection.querryDB(sql);
    }
    void displayLaps(DBConn connection, VBox root, List<Laptop> laptopList) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {
            int index = i;
            HBox laptopstBox = new HBox();
            Label lbId = new Label("" + laptopList.get(index).getId());
            Label lbName = new Label(laptopList.get(index).getName());
            Label lbImg = new Label("" + laptopList.get(index).getImg());

            Image image = new Image("" + laptopList.get(index).getImg());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(220);
            imageView.setPreserveRatio(true);
            Label lbPrice = new Label("" + laptopList.get(index).getPrice());
            Label lbCompany= new Label("" + laptopList.get(index).getCompany());

            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(actionEvent -> {
                System.out.println("Click delete " + laptopList.get(index).getId());
                toggle = 0;
                connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).getId());
                renderLaps(root, connection);
            });

            Button btnUpdate = new Button("Update");
            btnUpdate.setOnAction(actionEvent -> {

                if(toggle  == 0 ){
                    btnUpdate.setText("ok");
                    toggle = laptopList.get(index).getId();
                    beforeUpdate(laptopList.get(index).getId());
                    emptyStage.getChildren().addAll(inputLaptop);



                } else if(toggle == laptopList.get(index).getId()) {
                    toggle = 0;
                    update(laptopList.get(index).getId());
                    emptyStage.getChildren().remove(inputLaptop);

                    renderLaps(root, connection);
                }
                System.out.println("Click update" + laptopList.get(index).getId());
            });

            laptopstBox.setSpacing(42);
            laptopstBox.getChildren().addAll(lbId, lbName, lbImg,imageView, lbPrice,lbCompany, btnDelete, btnUpdate);
            root.getChildren().add(laptopstBox);
        }
    }

    void displayLaps(DBConn connection, VBox root, List<Laptop> laptopList, String proviso) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {

            int index = i;
            System.out.println(laptopList.get(index).getName());
            System.out.println(proviso);
            System.out.println(""+laptopList.get(index).getName().equals(proviso));

            String name = ""+laptopList.get(index).getName();

            if(name.equals(proviso)){
                HBox laptopstBox = new HBox();
                Label lbId = new Label("" + laptopList.get(index).getId());
                Label lbName = new Label(laptopList.get(index).getName());
                Label lbImg = new Label("" + laptopList.get(index).getImg());

                Image image = new Image("" + laptopList.get(index).getImg());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(220);
                imageView.setPreserveRatio(true);
                Label lbCompany= new Label("" + laptopList.get(index).getCompany());

                Label lbPrice = new Label("" + laptopList.get(index).getPrice());
                Button btnDelete = new Button("Delete");

                btnDelete.setOnAction(actionEvent -> {
                    System.out.println("Click delete " + laptopList.get(index).getId());

                    connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).getId());
                    renderLaps(root, connection);
                });

                laptopstBox.setSpacing(42);
                laptopstBox.getChildren().addAll(lbId, lbName, lbImg,imageView, lbPrice,lbCompany, btnDelete);
                root.getChildren().add(laptopstBox);
            }

        }
    }


    private void renderLaps(VBox root, DBConn connection){
        List<Laptop> products = connection.getLaps();
        displayLaps(connection, root, products);
    }
    private void renderLaps(VBox root, DBConn connection, String proviso){
        List<Laptop> products = connection.getLaps();
        displayLaps(connection, root, products, proviso);
        System.out.println(proviso);
    }



    public static void main(String[] args) {
        launch();
    }
}