package ua.kiev.prog.base;

public class User {
    private String login;
    private String pass;
    private String status;
    private String roomname;

    public User(String login, String pass, String status, String roomname) {
        this.login = login;
        this.pass = pass;
        this.status = status;
        this.roomname = roomname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean checkPassword(String pass) {
        if (this.pass.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    @Override
    public String toString() {
        return "login=" + login + ';' + "status=" + status + ';';
    }
}
