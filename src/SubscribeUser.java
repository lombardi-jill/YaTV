import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a query to subscribe a given user to an app.
 */
public class SubscribeUser{
    Connection conn;
    Scanner scan;
    public SubscribeUser(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Subscribes a user to the specified app
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getUsers();
        System.out.printf("Enter the user's email address: ");
        final String email = scan.nextLine();
        cq.getApps();
        System.out.printf("Enter the app to subscribe to: ");
        final String app = scan.nextLine();
        System.out.printf("Enter the expiration date of the subscription in the format YYYY-MM-DD: ");
        final String exp = scan.nextLine();
        System.out.printf("Enter the cost of the subscription (ex: 12.34): ");
        float cost = 0;
        //verifies that we were provided a float
        try{
             cost = Float.parseFloat(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter a numerical cost, such as 12.34. Please try again. ");
            try{
                cost = Float.parseFloat(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid number. Please start over.");
                this.Execute();
            }
        }

        final String sql = "INSERT INTO Subscription(UserEmail, AppName, ExpirationDate, Cost) " +
                "VALUES (?, ?, ?, ?);";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set params
            stmt.setString(1, email);
            stmt.setString(2, app);
            stmt.setString(3, exp);
            stmt.setFloat(4, cost);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                System.out.printf("User %s subscribed to %s.", email, app);
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUT, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }
    }
}
