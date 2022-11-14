package user;

public class UserDto {
    private String userID;
    private String userPassword;

    public UserDto(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userID='" + userID + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
