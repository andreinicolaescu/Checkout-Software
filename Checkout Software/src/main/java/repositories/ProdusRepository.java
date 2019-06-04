package repositories;


import model.Product;
import model.Produs;
import services.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdusRepository {

    private String url = "jdbc:mysql://localhost/shop";
    private String username = "root";
    private String password = "";

    private Database database = null;

    public void clearDatabase() {
        String sql = "DELETE from products";

        try(
                Connection con = DriverManager.getConnection(url,username,password);
                PreparedStatement statement = con.prepareStatement(sql);
                //PreparedStatement delete = con.prepareStatement(del);
        ) {

            statement.execute();

        } catch (Exception e) { //SQLException
            e.printStackTrace();
        }
    }

    public void setDatabase(Database d) {
        this.database = d;
    }

    public void adaugaProdus(Product p) {

        //String del = "DELETE from products";
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?)";
        try(
                Connection con = DriverManager.getConnection(url,username,password);
                PreparedStatement upload = con.prepareStatement(sql);
                //PreparedStatement delete = con.prepareStatement(del);
        ) {

            //delete.execute();

            int curr_id = p.getId();

            Product curr_product = database.getById(curr_id);

            upload.setInt(1, curr_product.getId());
            upload.setString(2, curr_product.getType());
            upload.setString(3, curr_product.getName());
            upload.setFloat(4, curr_product.price);
            upload.execute();

        } catch (Exception e) { //SQLException
            e.printStackTrace();
        }
    }

    /*public List<Product> findProduse(){
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM produse";

        try(
                Connection con = DriverManager.getConnection(url,username,password);
                PreparedStatement s = con.prepareStatement(sql);
                ResultSet rs = s.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                double pret = rs.getDouble("pret");

                list.add(new Product(id, nume, pret));
            }
        } catch (Exception e) { //SQLException
            e.printStackTrace();
        }

        return list;
    }*/

    public void stergeProdus(Produs p) {
        String sql = "DELETE FROM produse WHERE id = ?";

        try(
                Connection con = DriverManager.getConnection(url,username,password);
                PreparedStatement s = con.prepareStatement(sql);
        ) {
            s.setInt(1, p.getId());
            s.executeUpdate();
        } catch (Exception e) { //SQLException
            e.printStackTrace();
        }
    }

}
