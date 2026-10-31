package ic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TemperatureTypeDAO {
    public static List<TemperatureType> getAllTypes() throws SQLException {
        List<TemperatureType> types = new ArrayList<>();
        String sql = "SELECT id, type_name FROM degree_type";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                types.add(new TemperatureType(rs.getInt("id"), rs.getString("type_name")));
            }
        }
        return types;
    }
}
