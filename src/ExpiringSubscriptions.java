import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get all of the subscriptions for a given user which are expiring soon
 */
/*
-- 1 pt strong motivation: Users would likely want to be able to see what subscriptions they are
    not using when considering renewing their subscriptions
-- 1 point for left join
-- 1 pt non-aggregate function (datediff/concat)
-- 1 pt for where/having
-- 1 pt for group by
-- 1 pt order by
-6 pts total -> complex query.
 */
public class ExpiringSubscriptions {
    Connection conn;
    Scanner scan;
    public ExpiringSubscriptions(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints all subscriptions for a user which are expiring soon
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getUsers();
        System.out.printf("Enter the user you want to query by: ");
        final String email = scan.nextLine();

        final String sql = "SELECT s.AppName, CONCAT('$', s.Cost) AS Cost FROM Subscription s " +
                "LEFT JOIN  " +
                "( " +
                "SELECT v.AppHostedOn AS appName, COUNT(*) AS watchedVideos FROM Videos v  " +
                "JOIN WatchList wl " +
                "ON v.Id = wl.VideoId " +
                "WHERE wl.UserEmail = @user2 " +
                "GROUP BY v.AppHostedOn " +
                ") awl " +
                "ON s.AppName = awl.appName " +
                "WHERE DATEDIFF(s.ExpirationDate, NOW()) < 180 " +
                "AND s.UserEmail = ? " +
                "AND awl.watchedVideos IS NULL " +
                "ORDER BY s.Cost DESC, s.AppName DESC;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, email);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while(res.next()){
                    System.out.printf("%s, %s%n",res.getString("AppName"), res.getString("Cost"));
                    count++;
                }
                if (count == 0){
                    System.out.printf("This user does not have any expiring subscriptions.%n");
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
