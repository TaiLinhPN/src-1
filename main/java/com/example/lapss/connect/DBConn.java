package com.example.lapss.connect;

//import com.almasb.fxgl.net.Connection;
import com.example.lapss.objects.Cart;
import com.example.lapss.objects.Company;
import com.example.lapss.objects.Laptop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBConn {
    public Connection connection;

    public  DBConn(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lapdatabase","root","");
            System.out.println("conneted");
        } catch (SQLException e) {
            connection = null;
            System.out.println(e);
        }
    }

    public List<Laptop> getLaps(){
        ArrayList<Laptop> laptop = new ArrayList<>();
        try {
            ResultSet resul = connection.prepareStatement("SELECT * FROM `laptops`").executeQuery();
            while (resul.next()){
                int id = resul.getInt("id");
                String name = resul.getString("name");
                String img = resul.getString("img");
                String company = resul.getString("company");
                float price = resul.getFloat("price");
                laptop.add(new Laptop(id , price,name,img,company));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return laptop;

    }

    public List<Cart> getCart(){
        ArrayList<Cart> cartList = new ArrayList<>();
        try {
            ResultSet resul = connection.prepareStatement("SELECT * FROM `cart`").executeQuery();
            while (resul.next()){
                int idUser = resul.getInt("id_user");
                int idProduct = resul.getInt("id_product");

                cartList.add(new Cart(idUser , idProduct));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return cartList;

    }



    public  Laptop getLaps(int idLap){
        Laptop laptop = new Laptop();

        try {

            ResultSet resul = connection.prepareStatement("SELECT * FROM `laptops` where id =" + idLap).executeQuery();
            while (resul.next()) {
                int id = resul.getInt("id");
                String name = resul.getString("name");
                String img = resul.getString("img");
                String company = resul.getString("company");
                float price = resul.getFloat("price");


                laptop.setId(id);
                laptop.setName(name);
                laptop.setImg(img);
                laptop.setCompany(company);
                laptop.setPrice(price );


            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return laptop;

    }


    public List<Company> getCompanys(){
        ArrayList<Company> company = new ArrayList<>();
        try {
            ResultSet resul = connection.prepareStatement("SELECT * FROM `companys`").executeQuery();
            while (resul.next()){
                int id = resul.getInt("id");
                String name = resul.getString("name");

                System.out.println(id);
                System.out.println(name);

                company.add(new Company(id ,name));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return company;

    }


    public List<Laptop> getdatabase(String sql){
        ArrayList<Laptop> laptop = new ArrayList<>();
        try {
            ResultSet resul = connection.prepareStatement(sql).executeQuery();
            while (resul.next()){
                int id = resul.getInt("id");
                String name = resul.getString("name");
                String img = resul.getString("img");
                String company = resul.getString("company");
                float price = resul.getFloat("price");

                laptop.add(new Laptop(id , price,name,img,company));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return laptop;

    }




    public void querryDB(String sql){
        try {
            System .out.println(sql);
            connection.prepareStatement(sql).executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }




}

