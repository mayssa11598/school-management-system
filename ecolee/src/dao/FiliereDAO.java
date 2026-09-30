package dao;

import dbaseee.b;
import java.sql.*;
import java.util.*;

public class FiliereDAO {
    Connection con = b.getConnection();

    // Retourne un Map<id, nom> pour les filières
    public Map<Integer, String> listerMap() {
        Map<Integer, String> map = new HashMap<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nom FROM filiere");
            while(rs.next()) {
                map.put(rs.getInt("id"), rs.getString("nom"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // Ajouter une filière
    public void ajouter(String nom) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO filiere(nom) VALUES(?)");
            ps.setString(1, nom);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Supprimer une filière par ID
    public void supprimer(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM filiere WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Modifier une filière par ID
    public void modifier(int id, String nom) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE filiere SET nom = ? WHERE id = ?");
            ps.setString(1, nom);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Lister toutes les filières pour le JTable
    public List<Object[]> lister() {
        List<Object[]> liste = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nom FROM filiere");
            while(rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nom");
                liste.add(row);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
}
