package com.example.lapss;

import com.example.lapss.connect.DBConn;
import com.example.lapss.objects.Cart;
import com.example.lapss.objects.Laptop;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;



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
    boolean toggleNav = false;
    boolean toggleCart = false;
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



    int IdUser = 1;

    @Override
    public void start(Stage stage) throws IOException {
        Color c = Color.rgb(174,174,255);
        Alert alert = new Alert(AlertType.NONE);
        ScrollPane scrollPane = new ScrollPane();

        FlowPane root = new FlowPane(Orientation.VERTICAL );
        FlowPane laptopRoot = new FlowPane(Orientation.HORIZONTAL);
        VBox methodBox = new VBox();
        HBox btnsMethodBox = new HBox();
        VBox siteBar = new VBox();
        VBox content = new VBox();


        HBox headerBox = new HBox();
        Label nameId = new Label("Id" );
        Label nameHead = new Label("Name" );
        Label imageHead = new Label("Image" );
        Label priceHead = new Label("Price" );
        Label companyNameHead = new Label("Company" );
        Label handdelHead = new Label("Handle" );

        nameId.setMinWidth(55);
        nameHead.setMinWidth(145);
        imageHead.setMinWidth(260);
        priceHead.setMinWidth(140);
        companyNameHead.setMinWidth(100);
        companyNameHead.setMinWidth(195);
        handdelHead.setMinWidth(100);

        headerBox.getChildren().addAll(nameId,nameHead,imageHead,priceHead,companyNameHead,handdelHead);
        headerBox.setStyle("-fx-background-color: #AEAEFFFF;");
        headerBox.setMinHeight(30);


        Button btnNav = new Button("Homepage");
        btnNav.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(toggleNav){
                    btnNav.setText("Go Homepage");
                    toggleNav = false;
                    displayLaps(laptopRoot, getAlldata());
                }else {
                    btnNav.setText("Go Admin page");
                    toggleNav = true;
                    displayLapsCard(laptopRoot, getAlldata());
                }

            }
        });
        Button btnCart = new Button("Cart");
        btnCart.setMinWidth(75);
        btnCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(toggleCart){
                    btnCart.setText("Cart");
                    toggleCart = false;
                    displayLapsCard(laptopRoot, getAlldata());
                }else {

                    btnCart.setText("Close Cart");
                    toggleCart = true;
                    System.out.println("xxxxx");
                    displayCart(laptopRoot, getLapsInCart(IdUser));
                }

            }
        });


        Button btnAdd = new Button("Add product");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ tfName.getText()+"','"+ tfImg.getText()+"',"+ tfPrice.getText()+",'" + tfCompany.getText()+"')";
                connection.querryDB(sql);

                displayLaps(laptopRoot, getAlldata());
                Alert alert = new Alert(AlertType.NONE,
                        "Added success ",ButtonType.APPLY);
                alert.show();
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



        btnsMethodBox.getChildren().addAll(btnSearch,btnInput);
        inputLaptop.getChildren().addAll(tfName,tfImg,tfPrice,tfCompany);

        siteBar.getChildren().addAll(btnsMethodBox,emptyStage);
        content.getChildren().addAll(headerBox, laptopRoot);

        root.getChildren().addAll( btnNav,btnCart, siteBar,content);

        startProgram(laptopRoot,  connection);

        siteBar.setMinWidth(320);
//        siteBar.setMaxWidth(300);
//        siteBar.setStyle("-fx-background-color: #30353a;");
        scrollPane.setContent(root);

        Scene scene = new Scene(scrollPane, 1250, 600);

        stage.setTitle("Laptop Store");
        stage.setScene(scene);
        stage.show();

        System.out.println(inputBox);
        System.out.println(emptyStageChildren);
        // parent = VBox@64461e45


    }
    void beforeUpdate(int id){
        Alert alert = new Alert(AlertType.WARNING,
                "Are you sure to update?",ButtonType.APPLY);
        alert.show();

        Laptop a = connection.getLaps(id);

        tfName.setText(a.getName());
        tfImg.setText(a.getImg());
        tfPrice.setText(String.valueOf(a.getPrice()));
        tfCompany.setText(a.getCompany());


    }
    void update(int id){
        Alert alert = new Alert(AlertType.INFORMATION,
                "Updated success?",ButtonType.APPLY);
        alert.show();

        String sql = "UPDATE `laptops` SET `name`='"+ tfName.getText()+"',`price`="+ tfPrice.getText()+",`img`='"+ tfImg.getText()+"',`company`='"+ tfCompany.getText()+"' WHERE id ="+id;
        System.out.println(sql);
        connection.querryDB(sql);
    }
    void displayLaps( FlowPane root, List<Laptop> laptopList) {
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
            Label lbPrice = new Label("$" + laptopList.get(index).getPrice());
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
            lbName.setMinWidth(100);
            lbPrice.setMinWidth(100);
            lbCompany.setMinWidth(100);
        }
    }

    void displayCart( FlowPane root, List<Laptop> laptopList) {
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
                Alert alert = new Alert(AlertType.WARNING,
                        "Are you sure to Delete?",ButtonType.APPLY);
                alert.show();
                System.out.println("Click delete " + laptopList.get(index).getId());
                toggle = 0;
                connection.querryDB("DELETE  FROM `cart` WHERE id_product = " + laptopList.get(index).getId());
                displayCart(root, getLapsInCart(IdUser));
            });


            laptopstBox.setSpacing(42);
            laptopstBox.getChildren().addAll(lbId, lbName,imageView, lbPrice,lbCompany, btnDelete);
            root.getChildren().add(laptopstBox);

            lbName.setMinWidth(200);
            lbPrice.setMinWidth(200);
            lbCompany.setMinWidth(200);


        }
    }
    void displayLapsCard( FlowPane root, List<Laptop> laptopList) {
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
                connection.querryDB("INSERT INTO `cart`(`id_user`, `id_product`) VALUES ("+IdUser+","+laptopList.get(index).getId()+")");
                btnAddToCard.setText("added");

            });
            Button btnDetail = new Button("Detail");
            btnDetail.setOnAction(actionEvent -> {
                detail(root,connection.getLaps(laptopList.get(index).getId()));
            });

            HBox Btns = new HBox(btnDetail,btnAddToCard);
            laptopstBox.getChildren().addAll(imageView, lbName, lbPrice, Btns);
            laptopstBox.setSpacing(10);

            Btns.setSpacing(10);
            laptopstBox.setStyle("-fx-border-color: blue;");

            root.getChildren().add(laptopstBox);
            root.setMinWidth(800);

        }
    }
    void detail( FlowPane root, Laptop laptop) {
        root.getChildren().clear();

        HBox laptopstBox = new HBox();

        Image image = new Image("" + laptop.getImg());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(620);
        imageView.setPreserveRatio(true);

        Label lbName = new Label( laptop.getName());
        Label lbCompany = new Label( "Company: " +laptop.getCompany());
        Label lbPrice = new Label("Price: " + laptop.getPrice());

        Button btnAddToCard = new Button("Add to cast");
        btnAddToCard.setOnAction(actionEvent -> {
            connection.querryDB("INSERT INTO `cart`(`id_user`, `id_product`) VALUES ("+IdUser+","+laptop.getId()+")");
            btnAddToCard.setText("added");

        });


        VBox ìnforBox = new VBox(lbName,lbCompany, lbPrice,btnAddToCard);
        laptopstBox.getChildren().addAll(imageView, ìnforBox );
        laptopstBox.setSpacing(10);

        root.getChildren().add(laptopstBox);
        root.setMinWidth(800);

    }


    private List<Laptop> searchName(String name){
        String sql = "SELECT * FROM `laptops` WHERE name LIKE '%"+name+"%'";
        List<Laptop> laptopList = getdatatata(sql);
        return  laptopList;
    }
    private List<Laptop> searchCompany(String company){
        String sql = "SELECT * FROM `laptops` WHERE company LIKE '%"+company+"%'";
        List<Laptop> laptopList = getdatatata(sql);
        return  laptopList;
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

    private List<Laptop> getLapsInCart(int id){
        List<Cart> cartList = getAllCart();
        ArrayList<Laptop> laptops = new ArrayList<> ();

        System.out.println(id);

        for (int i = 0; i < cartList.size(); i++) {
            System.out.println(cartList.get(i).getIdUer());

            if(cartList.get(i).getIdUer() == id ){

                System.out.println(cartList.get(i).getIdProduct());
                laptops.add(connection.getLaps(cartList.get(i).getIdProduct()));
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

    public List<Cart> getAllCart(){
        List<Cart> data = null;
        data = connection.getCart();
        return data;
    }
    private void startProgram(FlowPane root, DBConn connection){
        List<Laptop> products = getAlldata();
        displayLaps( root, products);
    }

    public static void main(String[] args) {
        launch();
    }
}