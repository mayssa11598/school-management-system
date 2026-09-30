/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbaseee.b;
import java.sql.*;

public class UtilisateurDAO {

    Connection con = b.getConnection();

    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // si un résultat = login OK

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

