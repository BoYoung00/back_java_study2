package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserDto> getvalue() {
        List<UserDto> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select userid, userpassword from usertable order by userid desc";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery(sql)) {
                while (rs.next()) {
                    String userID = rs.getString(1);
                    String userPassword = rs.getString(2);

                    UserDto userdto = new UserDto(userID, userPassword);
                    list.add(userdto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

