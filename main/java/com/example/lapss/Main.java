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
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    int toggle = 0;
    boolean toggleMethod = false;
    boolean toggleSearch = false;
    boolean stateSearch = false;
    boolean toggleInput = false;
    TextField tfName = new TextField();
    TextField tfImg = new TextField();
    TextField tfPrice = new TextField();
    TextField tfPrice2 = new TextField();
    TextField tfCompany = new TextField();
    DBConn connection = new DBConn();
    VBox emptyStage = new VBox();
    HBox emptyStageChildren = new HBox();
    VBox inputBox = new VBox();
    VBox inputLaptop = new VBox();


    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = new ScrollPane();

        HBox root = new HBox();
        VBox laptopRoot = new VBox();
        VBox methodBox = new VBox();
        HBox btnsMethodBox = new HBox();
        VBox siteBar = new VBox();
        VBox content = new VBox();


        HBox headerBox = new HBox();
        Label nameHead = new Label("Name" );
        Label imageHead = new Label("Image" );
        Label priceHead = new Label("Price" );
        Label companyNameHead = new Label("Company" );

        headerBox.getChildren().addAll(nameHead,imageHead,priceHead,companyNameHead);
        headerBox.setSpacing(42);

        Button btnNav = new Button("Homepage");
        btnNav.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {



                String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ tfName.getText()+"','"+ tfImg.getText()+"',"+ tfPrice.getText()+",'" + tfCompany.getText()+"')";
                connection.querryDB(sql);

                displayLaps(laptopRoot, getAlldata());
            }
        });


        Button btnAdd = new Button("Add product");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ tfName.getText()+"','"+ tfImg.getText()+"',"+ tfPrice.getText()+",'" + tfCompany.getText()+"')";
                connection.querryDB(sql);

                displayLaps(laptopRoot, getAlldata());
            }
        });

        Button btnSearchName = new Button("search by name");
        btnSearchName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateSearch){

                    String proviso = tfName.getText();
                    displayLaps(laptopRoot, searchName(proviso));
                }else {
                    stateSearch = true;
                    emptyStageChildren.getChildren().clear();
                    emptyStageChildren.getChildren().add(btnSearchName);
                    inputBox.getChildren().addAll(tfName);
                }
            }
        });

        Button btnSearchCompany = new Button("search by Company");
        btnSearchCompany.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateSearch){
                    String proviso = tfCompany.getText();
                    displayLaps(laptopRoot, searchCompany(proviso));
                }else {
                    emptyStageChildren.getChildren().clear();
                    emptyStageChildren.getChildren().add(btnSearchCompany);
                    stateSearch = true;
                    inputBox.getChildren().addAll(tfCompany);
                }
            }
        });

        Button btnSearchPrice = new Button("search by price");
        btnSearchPrice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateSearch){
                    float price = Float.parseFloat(tfPrice.getText());
                    float price2 = Float.parseFloat(tfPrice2.getText());

                    displayLaps(laptopRoot,searchPrice(price,price2));
                }else {
                    emptyStageChildren.getChildren().clear();
                    emptyStageChildren.getChildren().add(btnSearchPrice);
                    stateSearch = true;
                    inputBox.getChildren().addAll(tfPrice,tfPrice2);
                }
            }
        });

        Button btnSearch = new Button("Search product");
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!toggleSearch){
                    btnSearch.setText("stop search");
                    toggleSearch = true;
                    emptyStageChildren.getChildren().addAll(btnSearchName,btnSearchCompany,btnSearchPrice);
                    emptyStage.getChildren().addAll(emptyStageChildren,inputBox);

                }else {
                    btnSearch.setText("Search product");
                    toggleSearch = false;
                    stateSearch = false;

                    emptyStageChildren.getChildren().clear();
                    inputBox.getChildren().clear();
                    emptyStage.getChildren().removeAll(emptyStageChildren,inputBox);

                    displayLaps(laptopRoot,getAlldata());
                }
            }
        });

        Button btnInput = new Button("Input product");
        btnInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!toggleInput){
                    btnInput.setText("Stop Input");
                    toggleInput = true;


                    inputBox.getChildren().addAll(tfName,tfImg,tfPrice,tfCompany);

                    emptyStageChildren.getChildren().addAll(btnAdd);
                    emptyStage.getChildren().addAll(emptyStageChildren,inputBox);

                }else {
                    btnInput.setText("Input product");
                    toggleInput = false;

                    emptyStageChildren.getChildren().clear();
                    inputBox.getChildren().clear();
                    emptyStage.getChildren().removeAll(emptyStageChildren,inputBox);

                    displayLaps(laptopRoot,getAlldata());
                }
            }
        });
//        Button btnOpenMetherdBox = new Button("Open Metherd Box");
//        btnOpenMetherdBox.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//                if(toggleMethod){
//                    toggleMethod = false ;
//                    btnOpenMetherdBox.setText("Open Metherd Box");
//
//
//                    methodBox.getChildren().removeAll(btnsMethodBox,emptyStageChildren);
//                    emptyStage.getChildren().removeAll(methodBox);
//                    inputBox.getChildren().removeAll(tfName);
//                    emptyStageChildren.getChildren().removeAll(btnSearchName,btnSearchCompany);
//
//                    if(toggleSearch){
//                        toggleSearch = false;
//                        btnSearch.setText("Search product");
//                    }
//                    stateSearch = false;
//
//
//
//                    emptyStage.getChildren().remove(methodBox);
//                } else {
//                    toggleMethod = true;
//                    btnOpenMetherdBox.setText("Close");
//                    methodBox.getChildren().addAll(btnsMethodBox,emptyStageChildren);
//
//
//                    emptyStage.getChildren().addAll(methodBox);
//
//                }
//            }
//        });

//                Button btnOpenMetherdBox = new Button("Open Metherd Box");
//        btnOpenMetherdBox.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//                if(toggleMethod){
//                    toggleMethod = false ;
//                    btnOpenMetherdBox.setText("Open Metherd Box");
//
//
//                } else {
//                    toggleMethod = true;
//                    btnOpenMetherdBox.setText("Close");
//
//
//                }
//            }
//        });


        btnsMethodBox.getChildren().addAll(btnSearch,btnInput);
        inputLaptop.getChildren().addAll(tfName,tfImg,tfPrice,tfCompany);

        siteBar.getChildren().addAll(btnsMethodBox,emptyStage);
        content.getChildren().addAll(headerBox, laptopRoot);

        root.getChildren().addAll(siteBar,content);
        getData(laptopRoot,  connection);

        siteBar.setMinWidth(300);
//        siteBar.setStyle("-fx-background-color: #30353a;");
        scrollPane.setContent(root);

        Scene scene = new Scene(scrollPane, 1200, 600);
        scene.getStylesheets().add("style.css");

        stage.setTitle("Laptop Store");
        stage.setScene(scene);
        stage.show();

        System.out.println(inputBox);
        System.out.println(emptyStageChildren);
        // parent = VBox@64461e45


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
    void displayLaps( VBox root, List<Laptop> laptopList) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {
            int index = i;
            HBox laptopstBox = new HBox();
            Label lbId = new Label("" + laptopList.get(index).getId());
            Label lbName = new Label(laptopList.get(index).getName());
//            Label lbImg = new Label("" + laptopList.get(index).getImg());

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
                displayLaps(root,getAlldata());
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
                    displayLaps(root,getAlldata());

                }
                System.out.println("Click update" + laptopList.get(index).getId());
            });

            laptopstBox.setSpacing(42);
            laptopstBox.getChildren().addAll(lbId, lbName,imageView, lbPrice,lbCompany, btnDelete, btnUpdate);
            root.getChildren().add(laptopstBox);
        }



    }

    void displayLapsCard( VBox root, List<Laptop> laptopList) {
        root.getChildren().clear();
        for (int i = 0; i < laptopList.size(); i++) {
            int index = i;
            VBox laptopstBox = new VBox();

            Image image = new Image("" + laptopList.get(index).getImg());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(220);
            imageView.setPreserveRatio(true);

            Label lbName = new Label( laptopList.get(index).getName());
            Label lbPrice = new Label("Price: " + laptopList.get(index).getPrice());

            Button btnAddToCard = new Button("Add to cast");
            btnAddToCard.setOnAction(actionEvent -> {
                System.out.println("Click delete " + laptopList.get(index).getId());
                toggle = 0;
                connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).getId());
//                displayLapsCard(root,getAlldata());
            });
            Button btnDetail = new Button("Detail");
            btnDetail.setOnAction(actionEvent -> {
                System.out.println("Click delete " + laptopList.get(index).getId());
                toggle = 0;
                connection.querryDB("DELETE  FROM `laptops` WHERE id = " + laptopList.get(index).getId());
//                displayLapsCard(root,getAlldata());
            });

            HBox Btns = new HBox(btnDetail,btnAddToCard);
            laptopstBox.getChildren().addAll(imageView, lbName, lbPrice, Btns);
            laptopstBox.setSpacing(10);
            Btns.setSpacing(10);
            laptopstBox.setStyle("-fx-border-color: blue;");

            root.getChildren().add(laptopstBox);
        }



    }
    // display(list)
    // getData() return data;
    // search(keyword) return data;

    private List<Laptop> searchName(String name){
        List<Laptop> laptopList = connection.getLaps();
        ArrayList<Laptop> laptops = new ArrayList<> ();

        for (int i = 0; i < laptopList.size(); i++) {
            if(laptopList.get(i).getName().equals(name)){
                laptops.add(laptopList.get(i));
            }
        }
        return  laptops;
    }
    private List<Laptop> searchCompany(String company){
        List<Laptop> laptopList = connection.getLaps();
        ArrayList<Laptop> laptops = new ArrayList<> ();

        for (int i = 0; i < laptopList.size(); i++) {
            if(laptopList.get(i).getCompany().equals(company)){
                laptops.add(laptopList.get(i));
            }
        }
        return  laptops;
    }
    private List<Laptop> searchPrice(float x, float y){
        List<Laptop> laptopList = connection.getLaps();
        ArrayList<Laptop> laptops = new ArrayList<> ();

        for (int i = 0; i < laptopList.size(); i++) {
            if(laptopList.get(i).getPrice() >= x && laptopList.get(i).getPrice() <= y ){
                laptops.add(laptopList.get(i));
            }
        }
        return  laptops;
    }
    public List<Laptop> getdatatata(String sql){
        List<Laptop> data = null;
        data = connection.getdatabase(sql);
        return data;
    }
    public List<Laptop> getAlldata(){
        List<Laptop> data = null;
        String sql = "SELECT * FROM `laptops`";
        data = connection.getdatabase(sql);
        return data;
    }
    private void getData(VBox root, DBConn connection){
        String sql = "SELECT * FROM `laptops`";
        List<Laptop> products = getdatatata(sql);
        displayLaps( root, products);
        displayLapsCard(root, products);
    }

    public static void main(String[] args) {
        launch();
    }
}