/*
 * Archivo de conexion a los datos del servidor.
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
//import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 *
 * @author dturcios
 */
public class MyDataSourceFactory {
    public static DataSource getMySQLDataSource() {
        Properties DConec = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = null;
        try {
            fis = new FileInputStream("db.properties");
            DConec.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(DConec.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(DConec.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(DConec.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
}
