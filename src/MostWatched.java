import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get a ranked list of the 10 most watched videos in the db
 */
public class MostWatched {
    Connection conn;
    Scanner scan;
    public MostWatched(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints a ranked list of top 10 watched videos
     */
    public void Execute() {
        final String sql = "SELECT Shows.Title, COUNT(*) AS WatchCount, v.AppHostedOn " +
                "FROM WatchList wl " +
                "JOIN Videos v ON v.Id = wl.VideoId " +
                "JOIN Episode e ON v.Id = e.VideoId " +
                "JOIN Shows ON e.ShowId = Shows.Id " +
                "GROUP BY Shows.Title " +
                "ORDER BY COUNT(*) DESC, Shows.Title ASC " +
                "LIMIT 10;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int inc = 1;
                while(res.next()){
                    System.out.printf("%d. %s on %s%n", inc, res.getString("Shows.Title"), res.getString("AppHostedOn"));
                    inc++;
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }
}
