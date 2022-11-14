package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserDao {
    private static String url = "jdbc:mysql://localhost:3306/userdatabase";
    private static String user="root";
    private static String password="rlaqhdud2@";

    public int addUser(UserDto dto) {
        int count = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "insert into usertable values (?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getUserID());
            ps.setString(2, dto.getUserPassword());

            count = ps.executeUpdate(); //객체 값 반환

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}

