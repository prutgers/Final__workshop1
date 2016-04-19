package workshop1;

/**
 *
 * @author Sonja
 */

import java.sql.*;
import java.util.ArrayList; 

public class AdresDAO {
    
    static PreparedStatement stmnt;

    public static void createAdres(Adres adres) {
        String query = "INSERT INTO adres ("
                + "straatnaam,"
                + "huisnummer,"
                + "toevoeging,"
                + "postcode,"
                + "woonplaats,"
                //+ "adres_id" let op: extra ? in values wanneer deze terug
                + ")"
                + "values (?, ?, ?, ?, ?)";
        try (Connection connection = new DBConnector().getConnection();){
            
            PreparedStatement stmntCA = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            
            stmntCA.setString(1, adres.getStraatnaam());
            stmntCA.setInt(2, adres.getHuisnummer());
            stmntCA.setString(3, adres.getToevoeging());
            stmntCA.setString(4, adres.getPostcode());
            stmntCA.setString(5, adres.getWoonplaats());
            //stmntCA.setInt(6, adres.getAdres_id()); ??
            
            stmntCA.executeUpdate();
            
            ResultSet resultSet = stmnt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()){
                resultSet.next();
                adres.setAdres_id(resultSet.getInt(1));
            }   
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex + "\nProbeer opnieuw.");
            }
    }
    
    public static ArrayList<Adres> readAdres() {
        ArrayList<Adres> adresGegevens = new ArrayList<>();
        try (Connection connection = new DBConnector().getConnection();) { 
            //Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stmntRA = connection.prepareStatement(
                    "SELECT FROM adres (straatnaam,"
                + "huisnummer,"
                + "toevoeging,"
                + "postcode,"
                + "woonplaats,"
                + "adres_id)");
            ResultSet rs = stmntRA.executeQuery();
            while (rs.next()) {
                Adres adres = new Adres();
                
                adres.setStraatnaam(rs.getString("straatnaam"));
                adres.setHuisnummer(rs.getInt("huisnummer"));
                adres.setToevoeging(rs.getString("toevoeging"));
                adres.setPostcode(rs.getString("postcode"));
                adres.setWoonplaats(rs.getString("woonplaats"));
                adres.setAdres_id(rs.getInt("adres_id"));
                
                adresGegevens.add(adres);
            }
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Probeer opnieuw.");
            }
        
        return adresGegevens;
    }
    
    public static Adres readAdresByID(int adresID) {
        Adres adres = new Adres();
    
        try (Connection connection = new DBConnector().getConnection();) {
            //Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement stmntRAID = connection.prepareStatement(
                    "SELECT FROM adres (straatnaam,"
                + "huisnummer,"
                + "toevoeging,"
                + "postcode,"
                + "woonplaats)"
                + "WHERE adres_id=?");
            stmntRAID.setInt(1, adresID);     
            ResultSet rs = stmntRAID.executeQuery();
            while (rs.next()) {
                adres.setStraatnaam(rs.getString("straatnaam"));
                adres.setHuisnummer(rs.getInt("huisnummer"));
                adres.setToevoeging(rs.getString("toevoeging"));
                adres.setPostcode(rs.getString("postcode"));
                adres.setWoonplaats(rs.getString("woonplaats"));
                adres.setAdres_id(rs.getInt("adres_id"));
            }
        }
            catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Probeer opnieuw.");
            }
        
        return adres;
    }
    
    public static void updateAdres(Adres adres) {
        String query = "UPDATE adres SET "
                + "straatnaam=?,"
                + "huisnummer=?,"
                + "toevoeging=?,"
                + "postcode=?,"
                + "woonplaats=?"
                + "WHERE adres_id=?";
        
        try (Connection connection = new DBConnector().getConnection();) {
            
            PreparedStatement stmntUA = connection.prepareStatement(query);
            
            stmntUA.setString(1, adres.getStraatnaam()); 
            stmntUA.setInt(2, adres.getHuisnummer());
            stmntUA.setString(3, adres.getToevoeging());
            stmntUA.setString(4, adres.getPostcode());
            stmntUA.setString(5, adres.getWoonplaats());
            
            stmntUA.executeUpdate();
            
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Probeer opnieuw.");
        }
    }
    
    public static void deleteAdres(int adres_id) {
        String query = "DELETE FROM adres straatnaam,"
                + "huisnummer,"
                + "toevoeging,"
                + "postcode,"
                + "woonplaats"
                + "WHERE adres_id=?";
        try (Connection connection = new DBConnector().getConnection();) {
            
            stmnt = connection.prepareStatement(query);
            stmnt.setInt(1, adres_id);
            stmnt.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Probeer opnieuw.");
        }
    }
}
