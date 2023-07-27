import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get the top 3 apps based on their support of a given tag
 */
/*
-- 2 pt for subquery (3 subqueries)
-- 1 pt strong motivation - Users can determine which apps on which platforms they should go to for certain content
-- 1 pt aggregate function (count)
-- 1 pt group by
-- 1 pt order by
-- 6 pts -> complex query
 */
public class Top3AppsForTag {
    Connection conn;
    Scanner scan;
    public Top3AppsForTag(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints the top 3 apps for videos of a given tag
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getTags();
        System.out.printf("Enter the tag you want to query by: ");
        final String tag = scan.nextLine();

        final String sql = "SELECT ao.Platform, winner.name AS appName, winner.tagCount, ao.Rating FROM AvailableOn ao  " +
                "JOIN ( " +
                "SELECT a.Name AS name, COUNT(vid.Tag) AS tagCount FROM App a  " +
                "JOIN ( " +
                "SELECT * FROM Videos v " +
                "JOIN ( " +
                "SELECT * FROM Tags t  " +
                "WHERE t.Tag = ? " +
                ") tag " +
                "ON v.Id = tag.VideoId " +
                ") vid  " +
                "ON vid.AppHostedOn = a.Name " +
                "GROUP BY a.Name " +
                "LIMIT 3 " +
                ") winner " +
                "ON ao.AppName = winner.name " +
                "ORDER BY winner.tagCount DESC, ao.Rating DESC, winner.name DESC;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, tag);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                while(res.next()){
                    System.out.printf("%s on %s%n",res.getString("appName"), res.getString("ao.Platform"));
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUTS, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }
    }
}
