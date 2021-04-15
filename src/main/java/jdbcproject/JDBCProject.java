package jdbcproject;


import java.sql.*;
import java.util.Scanner;

public class JDBCProject {

    static Scanner scanner = new Scanner(System.in);
    static Statement st;
    static Connection conn;
    static boolean loop = true;

    static {

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/artist_management", "root", "root");
            st = conn.createStatement();
        }
        catch(SQLException e) {

            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {

        while(loop) {

            menu();

        }
    }

    private static void menu() throws SQLException {

        JDBCProject project = new JDBCProject();

        System.out.println("1. Add Artist");
        System.out.println("2. Update Name");
        System.out.println("3. Update Age");
        System.out.println("4. Delete Artist");
        System.out.println("5. Show Artist by ID");
        System.out.println("6. Show All");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {

            case 0:
                loop = false;
                break;
            case 1:
                project.addArtist();
                break;
            case 2:
                project.updateName();
                break;
            case 3:
                project.updateAge();
                break;
            case 4:
                project.deleteArtist();
                break;
            case 5:
                project.showArtist();
                break;
            case 6:
                project.showAllArtists();
                break;
            default:
                break;

        }
    }



    public void addArtist() throws SQLException {

        PreparedStatement addArtist = conn.prepareStatement("insert into artists (firstname, lastname, age) values (?,?,?)");
        System.out.println("First name:");
        String firstname = scanner.nextLine();
        addArtist.setString(1, firstname);

        System.out.println("Last name:");
        String lastname = scanner.nextLine();
        addArtist.setString(2, lastname);

        System.out.println("Age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        addArtist.setInt(3, age);

        addArtist.executeUpdate();


    }

    public void updateName() throws SQLException {

        PreparedStatement updateName = conn.prepareStatement("update artists set firstname = ?, lastname = ? where id = ?");
        System.out.println("ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        updateName.setInt(3, id);

        System.out.println("First name:");
        String firstname = scanner.nextLine();
        updateName.setString(1, firstname);

        System.out.println("Last name:");
        String lastname = scanner.nextLine();
        updateName.setString(2, lastname);

        updateName.executeUpdate();

    }

    public void updateAge() throws SQLException {

        PreparedStatement updateAge = conn.prepareStatement("update artists set AGE = ? where ID = ?");
        System.out.println("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        updateAge.setInt(2, id);

        System.out.println("AGE: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        updateAge.setInt(1, age);

        updateAge.executeUpdate();
    }

    private void deleteArtist() throws SQLException {

        PreparedStatement deleteArtist = conn.prepareStatement("delete from artists where ID = ?");
        System.out.println("ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        deleteArtist.setInt(1, id);

        deleteArtist.executeUpdate();
    }

    public void showArtist() throws SQLException  {

        PreparedStatement showArtist = conn.prepareStatement("select * from artists where ID = ?");
        System.out.println("ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        showArtist.setInt(1, id);

        ResultSet rs = showArtist.executeQuery();

        System.out.println("-------------------------------------------------");

        while(rs.next()) {

            System.out.println("ID: " + rs.getInt(1) + " Firstname: " + rs.getString(2) +
                    " Lastname: " + rs.getString(3) + " Age: " + rs.getInt(4));
        }

        System.out.println("---------------------------------------------------");

    }

    public void showAllArtists() throws SQLException {

        ResultSet rs = st.executeQuery("select * from artists");

        System.out.println("---------------------------------------------------------");

        while(rs.next()) {

            System.out.println("id: " + rs.getInt(1) + " Firstname: " + rs.getString(2) +
                    " Lastname: " + rs.getString(3) + " Age: " + rs.getInt(4));
        }

        System.out.println("---------------------------------------------------------");

    }
}
