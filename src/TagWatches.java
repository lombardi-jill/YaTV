import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get the top three most watched video tags
 */
public class TagWatches {
    Connection conn;
    Scanner scan;

    public TagWatches(Connection conn, Scanner scan) {
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints the top-three most watched tags.
     */
    public void Execute() {

        final String sql = "SELECT t.Tag AS TagName, COUNT(t.VideoId) AS WatchCount FROM VideoList vl " +
                "LEFT JOIN Tags t ON t.VideoId = vl.VideoId " +
                "GROUP BY t.Tag " +
                "ORDER BY COUNT(t.Tag) DESC " +
                "LIMIT 3;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // get results
            int inc = 1;
            try (final ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    System.out.printf("%d. %s, watched %d times%n", inc, res.getString("TagName"), res.getInt("WatchCount"));
                    inc++;
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }
}
