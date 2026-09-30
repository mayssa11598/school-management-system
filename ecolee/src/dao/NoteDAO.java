package dao;

import dbaseee.b;
import java.sql.*;
import java.util.*;

public class NoteDAO {

    Connection con = b.getConnection();

    public void ajouter(int eleveId, int matiereId, double note){
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO note(eleve_id, matiere_id, note) VALUES (?,?,?)"
            );
            ps.setInt(1, eleveId);
            ps.setInt(2, matiereId);
            ps.setDouble(3, note);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void modifier(int id, int eleveId, int matiereId, double note){
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE note SET eleve_id=?, matiere_id=?, note=? WHERE id=?"
            );
            ps.setInt(1, eleveId);
            ps.setInt(2, matiereId);
            ps.setDouble(3, note);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void supprimer(int id){
        try {
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM note WHERE id=?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Object[]> lister(){
        List<Object[]> liste = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT n.id, e.nom AS eleve, m.nom AS matiere, n.note " +
                "FROM note n " +
                "JOIN eleve e ON n.eleve_id=e.id " +
                "JOIN matiere m ON n.matiere_id=m.id"
            );
            while (rs.next()) {
                liste.add(
                    new Object[]{
                        rs.getInt("id"),
                        rs.getString("eleve"),
                        rs.getString("matiere"),
                        rs.getDouble("note")
                    }
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return liste;
    }
    public List<Object[]> listerParEleve(int eleveId) {
    List<Object[]> liste = new ArrayList<>();
    try {
        String sql = "SELECT m.nom AS matiere, m.coeff, n.note " +
                     "FROM note n " +
                     "JOIN matiere m ON n.matiere_id = m.id " +
                     "WHERE n.eleve_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, eleveId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            liste.add(new Object[]{
                rs.getString("matiere"),
                rs.getDouble("coeff"),
                rs.getDouble("note")
            });
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
    return liste;
}

}
