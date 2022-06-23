package com.example.lapss;


import com.example.lapss.objects.Laptop;
import com.example.lapss.objects.UserAccount;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewDemo2 extends Application {

    @Override
    public void start(Stage stage) {

        TableView<Laptop> table = new TableView<Laptop>();


        // Tạo cột UserName (Kiểu dữ liệu String)
        TableColumn<Laptop, String> userNameCol //
                = new TableColumn<Laptop, String>("User Name");

        // Tạo cột Email (Kiểu dữ liệu String)
        TableColumn<Laptop, String> emailCol//
                = new TableColumn<Laptop, String>("Email");
        // Tạo cột FullName (Kiểu dữ liệu String)


        // Thêm 2 cột con vào cột FullName


        // Active Column


        // Định nghĩa cách để lấy dữ liệu cho mỗi ô.
        // Lấy giá trị từ các thuộc tính của UserAccount.
//        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        emailCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//


        // Sét xắp xếp theo userName



        // Hiển thị các dòng dữ liệu
        ObservableList<Laptop> list = getUserList();
        table.setItems(list);

        table.getColumns().addAll(userNameCol, emailCol);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);



        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Laptop> getUserList() {

        Laptop user1 = new Laptop(1,2222, "Doe", "https://hc.com.vn/i/ecommerce/media/GS.008256_FEATURE_88774.jpg","dell");
        Laptop user2 = new Laptop(2,2222, "Doee", "https://hc.com.vn/i/ecommerce/media/GS.008256_FEATURE_88774.jpg","dell");
        Laptop user3 = new Laptop(3,2222, "Doew", "https://hc.com.vn/i/ecommerce/media/GS.008256_FEATURE_88774.jpg","dell");

        ObservableList<Laptop> list = FXCollections.observableArrayList(user1, user2, user3);
        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}