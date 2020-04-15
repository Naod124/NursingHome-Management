package sample.model;

public class LogIn {
    private String userName;
    private String PassWord;

    public LogIn(String userName, String passWord) {
        this.userName = userName;
        PassWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
