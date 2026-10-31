package ic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TemperatureDAO {
    public void save(Temperature temp) throws SQLException {
        String sql = "INSERT INTO history (originalType, originalDegree, extreme, convertedType, convertedDegree) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, temp.getOriginalType());
            ps.setDouble(2, temp.getOriginalDegree());
            ps.setBoolean(3, temp.getExtreme());
            ps.setString(4, temp.getConvertedType());
            ps.setDouble(5, temp.getConvertedDegree());
            ps.executeUpdate();
        }
    }

    public List<Temperature> getHistory() throws SQLException {
        List<Temperature> history = new ArrayList<>();
        String sql = "SELECT * FROM history";

        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                history.add(new Temperature(
                        rs.getInt("id"),
                        rs.getString("originalType"),
                        rs.getDouble("originalDegree"),
                        rs.getBoolean("extreme"),
                        rs.getString("convertedType"),
                        rs.getDouble("convertedDegree")
                        )
                );
            }
        }
        return history;
    }
}
