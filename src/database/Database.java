/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://www.journaldev.com/2509/java-datasource-jdbc-datasource-example

Insert examples: https://alvinalexander.com/java/java-mysql-insert-example-statement
https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement

 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 *
 * @author dturcios
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        testDataSource("mysql");
	System.out.println("Los datos se han introducido correctamente");
    }

    private static void testDataSource(String dbType) {
        DataSource ds = null;
        if ("mysql".equals(dbType)) {
            ds = MyDataSourceFactory.getMySQLDataSource();
        } else {
            System.out.println("Base de datos invalida");
            return;
        }

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from usuarios");
            int IdUser=0;
            while (rs.next()) {
                System.out.println("IdUser =" + rs.getInt("IdUser") + ", Nombre=" + rs.getString("nombre") + " email= " +rs.getString("email"));
                IdUser= rs.getInt("IdUser");
            }
            String query = " insert into usuarios (IdUser, nombre, email)" + " values (?,?,?) ";
            PreparedStatement preStmt  = con.prepareStatement(query);
            
            IdUser++;
            preStmt.setInt(1, IdUser );
            preStmt.setString(2, "maria martines");
            preStmt.setString(3, "maria@gmail.com");
            preStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
