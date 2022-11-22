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

    public int deleteuser(String userid) {
        int deleteCount = 0;

        Connection conn = null;

        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "delete from usertable where userid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            deleteCount = ps.executeUpdate(); //int값 반환

        }catch (Exception e) {
            e.printStackTrace();
        }

        finally { //무조건 한 번 실행 시키기
            if (ps != null) {
                try {
                    ps.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return deleteCount;
    }

    public int update(UserDto dto) {
        int updateCount=0;

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql="update usertable set userpassword=? where userid=?";
            ps=conn.prepareStatement(sql);

            ps.setString(1, dto.getUserPassword());
            ps.setString(2, dto.getUserID());

            updateCount=ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        finally { //무조건 한 번 실행 시키기
            if (ps != null) {
                try {
                    ps.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return updateCount;
    }

    public int loginUser(UserDto dto) {

        String sql = "SELECT userPassword FROM usertable WHERE userid=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //드라이버 연결
            Connection conn = DriverManager.getConnection(url, user, password); //DB연결
            PreparedStatement ps = conn.prepareStatement(sql); //쿼리 연결
            ps.setString(1, dto.getUserID());

            ResultSet rs = ps.executeQuery(); //값으로 뭔갈 해야할 때, 결과 값 저장 (객체값 반환함 => 행)
            if (rs.next()) {
                if (rs.getString(1).equals(dto.getUserPassword())) {
                    return 1; //성공
                }
                else {
                    return 0; //하나 일치하지 않음
                }
            }

            return  -1; //일치한거 없음

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -2; //DB 오류
    }


}

