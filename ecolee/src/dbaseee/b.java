/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaseee;
import java.sql.*;

public class b {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecolee", "root", ""
                );
                System.out.println("Connexion réussie !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
