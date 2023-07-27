import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to produce the hit to like ratio of videos which are not episodes of a show and which have been
 * watched by at least one user.
 */
/*
-- 1 pt left join
-- 1 pt for 3 tables joined
-- 1 pt for group by
-- 1 pt for aggregate functions (count/sum)
-- 1 pt for order by
-- 1 pt for non-aggregate function (round/concat)
-- 6 pts -> complex query
 */
public class VidLikeToHitsRatio {
    Connection conn;
    Scanner scan;

    public VidLikeToHitsRatio(Connection conn, Scanner scan) {
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints the likes percent of all non-show videos
     */
    public void Execute() {
        final String sql = "SELECT v.Title, COUNT(wl.UserEmail) AS hits, SUM(wl.isLiked) AS likes, "+
                "CONCAT( ROUND(SUM(wl.isLiked) / COUNT(wl.UserEmail), 2)* 100, '%') AS likedPercent  " +
                "FROM Videos v " +
                "LEFT JOIN Episode e " +
                "ON v.Id = e.VideoId " +
                "JOIN WatchList wl  " +
                "ON wl.VideoId = v.Id " +
                "WHERE e.ShowId IS NULL " +
                "GROUP BY wl.VideoId " +
                "ORDER BY ROUND(SUM(wl.isLiked) / COUNT(wl.UserEmail), 2) DESC, likes DESC;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    System.out.printf("%s: %s%n", res.getString("v.Title"), res.getString("likedPercent"));
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }
}
