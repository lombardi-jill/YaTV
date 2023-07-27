
import java.sql.*;
import java.util.Scanner;

/**
 * Represents a query to register a new user for the app.
 */
public class RegisterUser {
    Connection conn;
    Scanner scan;
    public RegisterUser(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Registers a new user to the db
     */
    public void Execute() {
        System.out.printf("Enter the new user's email address: ");
        final String email = scan.nextLine();
        System.out.printf("Enter the new user's first name: ");
        final String fname = scan.nextLine();
        System.out.printf("Enter the new user's last name: ");
        final String lname = scan.nextLine();
        System.out.printf("Enter the new user's password: ");
        final String pass = scan.nextLine();
        System.out.printf("Enter the new user's country: ");
        final String country = scan.nextLine();

        //sets auto commit to false - allows for transaction grouping
        try{
            conn.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }

        final String insertSql = "INSERT INTO User(Email, FirstName, LastName, Password, Country)" +
                " VALUES (?, ?, ?, ?, ?);";
        final String saltSql = "UPDATE User " +
                "SET PassSalt = SUBSTRING(MD5(RAND()), -10);";
        final String hashSql = "UPDATE User u " +
                "SET u.Password = SHA2(CONCAT(u.Password, PassSalt), 256);";

        // prepare statement
        try (final PreparedStatement insertStmt = conn.prepareStatement(insertSql);
        final PreparedStatement saltStmt = conn.prepareStatement(saltSql);
        final PreparedStatement hashStmt = conn.prepareStatement(hashSql))
        {
            // set params
            insertStmt.setString(1, email);
            insertStmt.setString(2, fname);
            insertStmt.setString(3, lname);
            insertStmt.setString(4, pass);
            insertStmt.setString(5, country);

            try(final ResultSet res1 = insertStmt.executeQuery();
                final ResultSet res2 = saltStmt.executeQuery();
                final ResultSet res3 = hashStmt.executeQuery()){
                //commits the transaction
                conn.commit();
                conn.setAutoCommit(true);
                System.out.printf("User %s %s has been added to the system.", fname, lname);
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
