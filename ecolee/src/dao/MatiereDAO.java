/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package dao;

import dbaseee.b;
import java.sql.*;
import java.util.*;

public class MatiereDAO {
    Connection con = b.getConnection();

   public void ajouter(String nom, double coeff){
    try{
        String sql = "INSERT INTO matiere(nom, coeff) VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setDouble(2, coeff);
        ps.executeUpdate();
    } catch(Exception e){ e.printStackTrace(); }
}

public void modifier(int id, String nom, double coeff){
    try{
        String sql = "UPDATE matiere SET nom=?, coeff=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setDouble(2, coeff);
        ps.setInt(3, id);
        ps.executeUpdate();
    } catch(Exception e){ e.printStackTrace(); }
}
public void supprimer(int id){
        try{
            String sql = "DELETE FROM matiere WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }

public List<Object[]> lister(){
    List<Object[]> liste = new ArrayList<>();
    try{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM matiere");
        while(rs.next()){
            liste.add(new Object[]{rs.getInt("id"), rs.getString("nom"), rs.getDouble("coeff")});
        }
    } catch(Exception e){ e.printStackTrace(); }
    return liste;
}

}
