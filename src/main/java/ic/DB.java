package ic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        boolean hasDB = checkDBExists(DriverManager.getConnection(URL, USER, PASSWORD));
        

        if (!hasDB) {
            System.out.println("no database found, creating new database");
            conn = createDB();
        }
        return conn;
    }

    public static boolean ensureDBExists() throws SQLException {
        boolean hasDB = checkDBExists(DriverManager.getConnection(URL, USER, PASSWORD));
        
        if (!hasDB) {
            System.out.println("no database found, creating new database");
            createDB();
        }

        return hasDB;
    }

    private static boolean checkDBExists(Connection conn) throws SQLException {
        boolean dbExists = false;

        // Source - https://stackoverflow.com/a/12414762
        // Posted by mguymon, modified by community. See post 'Timeline' for change history
        // Retrieved 2026-10-31, License - CC BY-SA 4.0
        ResultSet dbSet = conn.getMetaData().getCatalogs();
        // Iterate each catalog in the ResultSet
        while (dbSet.next()) {
            System.out.println(dbSet.getString(1));
            // Get the database name, which is at position 1
            if (dbSet.getString(1).contains(DATABASE)) {
                dbExists = true;
            }
        }
        dbSet.close();

        return dbExists;
    }

    private static Connection createDB() throws SQLException {
        Connection rootConnection = DriverManager.getConnection(URL, USER, PASSWORD);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("icdb.sql"));
            Statement rootStatement = rootConnection.createStatement();
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines and single-line comments.
                if (line.isEmpty() || line.startsWith("--"))
                    continue;

                builder.append(line);
                if (line.endsWith(";"))
                try {
                    // Execute the SQL command
                    rootStatement.execute(builder.toString());
                    // Print a success message along with
                    // the first 15 characters of the
                    // executed command.
                    System.out.println(
                        " Command successfully executed : "
                        + builder.substring(
                            0,
                            Math.min(builder.length(), 15))
                        + "...");
                    builder.setLength(0);
                }
                catch (SQLException e) {
                    // If an SQLException occurs during
                    // execution, print an error message and
                    // stop further execution.
                    System.err.println(e.getMessage() + "\n");
                }
            }
            reader.close();

            rootStatement.execute(builder.toString());

            boolean dbCreated = checkDBExists(rootConnection);
            if (dbCreated) {
                System.out.println("new database created");
            } else {
                System.out.println("db creation failed");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
    }
}
/*
// Source - https://stackoverflow.com/a/12414762
// Posted by mguymon, modified by community. See post 'Timeline' for change history
// Retrieved 2026-10-31, License - CC BY-SA 4.0

// Connection connection = <your java.sql.Connection>
ResultSet resultSet = connection.getMetaData().getCatalogs();

// Iterate each catalog in the ResultSet
while (resultSet.next()) {
  // Get the database name, which is at position 1
  String databaseName = resultSet.getString(1);
}
resultSet.close();
*/