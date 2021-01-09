import java.util.HashMap;
public class User {
    String userName;
    HashMap<String, String> userList = new HashMap<String, String>();
    public User(){}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public HashMap<String, String> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, String> userList) {
        this.userList = userList;
    }

    public void addUser(String userName){
        userList.put(userName,userType.VIEWER.name());
    }

    public void changeUserTypeToCritic (String userName){
        if(userList.get(userName) != null){
            userList.put(userName,userType.CRITIC.name());
        }
    }

}
