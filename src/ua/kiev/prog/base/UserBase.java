package ua.kiev.prog.base;

import java.util.ArrayList;

public class UserBase {
    private static final UserBase userBase = new UserBase();
    private final ArrayList<User> base;

    private UserBase() {
        base = new ArrayList<>();
        this.base.add(new User("user1", "password1", "offline", "main"));
        this.base.add(new User("user2", "password2", "offline", "main"));
        this.base.add(new User("user3", "password3", "offline", "main"));
        this.base.add(new User("user4", "password4", "offline", "main"));
        this.base.add(new User("user5", "password5", "offline", "main"));
        this.base.add(new User("user6", "password6", "offline", "main"));
        this.base.add(new User("user7", "password7", "offline", "main"));
        this.base.add(new User("user8", "password8", "offline", "main"));
        this.base.add(new User("user9", "password9", "offline", "main"));
        this.base.add(new User("user10", "password10", "offline", "main"));
    }

    public static UserBase getInstance() {
        return userBase;
    }

    public boolean checkPassword(String login, String password) {
        for (int i = 0; i < base.size(); i++) {
            if ((base.get(i).getLogin().equals(login)) && (base.get(i).checkPassword(password))) {
                return true;
            }
        }

        return false;
    }

    public void changeStatus(String login, String status) {
        for (User user : base) {
            if (user.getLogin().equals(login)) {
                user.setStatus(status);
            }
        }
    }

    public void changeRoom(String room, String login) {
        for (User user : base) {
            if (user.getLogin().equals(login)) {
                user.setRoomname(room);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : base) {
            stringBuilder.append(user.getLogin() + " " + user.getStatus() + ";");
        }

        return stringBuilder.toString();
    }
}
