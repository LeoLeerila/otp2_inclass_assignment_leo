package ic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    //private static final String URL = "jdbc:mariadb://host.docker.internal:3306/svg_travel_db";
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String DATABASE = "icdb";
    private static final String USER = "root";
    private static final String PASSWORD = "example";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
        if (conn == null) {
            conn = createDB();
        }
        return conn;
    }

    private static Connection createDB() throws SQLException {
        Connection rootConnection = DriverManager.getConnection(URL, USER, PASSWORD);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("icdb.sql"));
            Statement rootStatement = rootConnection.createStatement();

            String line;
            while ((line = reader.readLine()) != null) {
                rootStatement.execute(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
    }
}
