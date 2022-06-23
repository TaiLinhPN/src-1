package com.example.lapss.connect;

import com.example.lapss.objects.Laptop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.DriverManager;


public class  HandleLaptop {
    public Connection connection;

    public  HandleLaptop(){
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
                String compary = resul.getString("company");
                float price = resul.getFloat("price");
                System.out.println(id);
                System.out.println(name);

                laptop.add(new Laptop(id , price,name,img,compary));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return laptop;

    }

    void createLaps(String name,String img , String company,  float price){
        String sql = "INSERT INTO `laptops`(`name`, `img`, `price`,`company`) VALUES ('"+ name+"','"+ img+"',"+ price+",'" + company+"')";
        try {
            System.out.println(sql);
            connection.prepareStatement(sql).executeUpdate();
            getLaps();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

      void deleteLap(int id){
        try {
             connection.prepareStatement("DELETE  FROM `laptops` WHERE id = "+id).executeUpdate();
            getLaps();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void updateLaptop(String name, String img, String company, float price, int id){
        String sql = "UPDATE `laptops` SET `name`='"+ name+"',`img`='"+ img+ "',`company`='"+ company+ "',`price`="+ price+ " WHERE id ="+ id;
        try {
            System.out.println(sql);
            connection.prepareStatement(sql).executeUpdate();
            getLaps();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    
}
