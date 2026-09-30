package dao;

import dbaseee.b;
import java.sql.*;
import java.util.*;

public class EnseignantDAO {
    Connection con = b.getConnection();

    public void ajouter(String nom, String prenom, String email, String telephone) {
        try {
            String sql = "INSERT INTO enseignant(nom, prenom, email, telephone) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, telephone);
            ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public void modifier(int id, String nom, String prenom, String email, String telephone) {
        try {
            String sql = "UPDATE enseignant SET nom=?, prenom=?, email=?, telephone=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, telephone);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM enseignant WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public List<Object[]> lister() {
        List<Object[]> liste = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM enseignant");
            while(rs.next()) {
                liste.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("telephone")
                });
            }
        } catch(Exception e){ e.printStackTrace(); }
        return liste;
    }
}
