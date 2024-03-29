package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //fetching all the (inputed)details of user
            String name = request.getParameter("user_name");
            String email = request.getParameter("user_email");
            String password = request.getParameter("user_pass");
            
             try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            
            //connecting to the database
            try{
                //loading the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
                //establishing connection
                String url = "jdbc:mysql://localhost:3306/STUDENTS?zeroDateTimeBehavior=CONVERT_TO_NULL";
                String usr = "root";
                String pass = "Ibergx00@stepup";
                //Establishing connection
               try (Connection connect=DriverManager.getConnection(url,usr,pass)){
                    // query
                    String q = "insert into students(name,email,password) values(?,?,?)";
                  //enabling to send SQL commands to DB
                    try(PreparedStatement pst = connect.prepareStatement(q)){
                    pst.setString(1,name);
                    pst.setString(2,email);
                    pst.setString(3,password);
                    
                    pst.executeUpdate();
                    out.println("done...");
                    }
                    connect.close();
               }
            } catch (ClassNotFoundException | SQLException e){
                out.println(e.getMessage() + "<h1>error ...</h1>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
