import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Command line application for the YaTV Database.
 */
public class YaTVApp {
    /**
     * Usage statement of supported queries
     */
    private static void _usage() {
        System.out.printf("1) Register a new user%n"); //5 inputs
        System.out.printf("2) Subscribe a user to a new app%n"); //2 inputs
        System.out.printf("3) Add a show to a user's list%n"); //2 inputs
        System.out.printf("4) Update app's version on a specified platform%n"); // 2 inputs
        System.out.printf("5) Add the latest episode of a show's current season%n"); // 7 inputs
        System.out.printf("6) Produces a ranked list of the top 10 most watched videos%n"); // 0 inputs
        System.out.printf("7) Find all free videos on a particular platform%n"); // 1 inputs
        System.out.printf("8) Find all long videos which were released this year and are not part of any show%n"); // 0 inputs
        System.out.printf("9) Produce a ranked list of revenue generated by apps in a country%n"); // 1 inputs
        System.out.printf("10) Produce a ranked list of watch counts from the top-3 video tags%n"); // 0 inputs
        System.out.printf("11) Produce a list of shows that a user has access to which has at least the given number of seasons%n"); // 2 inputs
        System.out.printf("12) Produce the likes-to-hits ratio for all non-show related videos which have been watched at least once.%n"); // 0 inputs
        System.out.printf("13) Produce a ranked list of countries by revenue for a given platform%n"); // 1 inputs
        System.out.printf("14) Find the top three apps which support the most videos with a specified tag%n"); // 1 inputs
        System.out.printf("15) Find all the unused, expiring subscriptions for a specified user%n%n"); // 0 inputs
    }

    /**
     * Command-line YaTVApp
     *
     * @param args n/a
     * @throws ClassNotFoundException cannot find JDBC driver
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // makes a connection to the database
        Class.forName("org.mariadb.jdbc.Driver");
        final String CONN_URI = ("jdbc:mariadb://localhost:3306/YaTV");
        try (final Connection connection = DriverManager.getConnection(CONN_URI, "root", "");
             final Scanner input = new Scanner(System.in)) {
            System.out.printf("Welcome to YaTV! Your home for video streaming.");
            System.out.println();
            run(input, connection);
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Calls appropriate query task
     *
     * @param input Scanner
     * @param connection
     */
    static void run(Scanner input, Connection connection) {
        // get queryType
        int param;
        _usage();
        System.out.printf("Enter a query number for the task you are interested in: ");
        try {
            param = Integer.parseInt(input.nextLine());

        } catch (NumberFormatException e) {
            param = -1;
            System.out.println();
            System.out.printf("You did not enter a valid query number. Please enter an integer between 1 and 15.%n");
            run(input, connection);
        }

        //calls the correct query based on user input
        switch (param) {
            case 1:
                new RegisterUser(connection, input).Execute();
                break;
            case 2:
                new SubscribeUser(connection, input).Execute();
                break;
            case 3:
                new AddShow(connection, input).Execute();
                break;
            case 4:
                new UpdateAppVersion(connection, input).Execute();
                break;
            case 5:
                new AddVidToSeason(connection, input).Execute();
                break;
            case 6:
                new MostWatched(connection, input).Execute();
                break;
            case 7:
                new GetAllFree(connection, input).Execute();
                break;
            case 8:
                new LongVideos(connection, input).Execute();
                break;
            case 9:
                new CountryRevenue(connection, input).Execute();
                break;
            case 10:
                new TagWatches(connection, input).Execute();
                break;
            case 11:
                new GetBingeWorthyShows(connection, input).Execute();
                break;
            case 12:
                new VidLikeToHitsRatio(connection, input).Execute();
                break;
            case 13:
                new RevenueOfPlatformPerCountry(connection, input).Execute();
                break;
            case 14:
                new Top3AppsForTag(connection, input).Execute();
                break;
            case 15:
                new ExpiringSubscriptions(connection, input).Execute();
                break;
            default:
                System.out.println();
                System.out.printf("You did not enter a valid query number. Please enter an integer between 1 and 15.%n");
                run(input, connection);
        }
        System.out.println();
        System.out.printf("Would you like to run another query? Y/N?: ");
        String res = input.nextLine();
        if (res.equals("Y")) {
            run(input, connection);
        } else {
            System.exit(0);
        }
    }
}


