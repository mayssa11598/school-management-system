package dao;

import dbaseee.b;
import java.sql.*;
import java.util.*;

public class EleveDAO {
    Connection con = b.getConnection();

    public void ajouter(String nom, String prenom, int filiere_id, String sexe) {
        try {
            String sql = "INSERT INTO eleve(nom, prenom, filiere_id, sexe) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setInt(3, filiere_id);
            ps.setString(4, sexe);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void modifier(int id, String nom, String prenom, int filiere_id, String sexe) {
        try {
            String sql = "UPDATE eleve SET nom=?, prenom=?, filiere_id=?, sexe=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setInt(3, filiere_id);
            ps.setString(4, sexe);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM eleve WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
    }

    public List<Object[]> lister() {
        List<Object[]> liste = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT e.id, e.nom, e.prenom, e.sexe, f.nom AS filiere " +
                "FROM eleve e LEFT JOIN filiere f ON e.filiere_id = f.id"
            );
            while(rs.next()) {
                liste.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("sexe"),
                    rs.getString("filiere")
                });
            }
        } catch(Exception e) { e.printStackTrace(); }
        return liste;
    }
  

}
