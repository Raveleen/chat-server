package ua.kiev.prog.base;

import java.util.HashMap;

public class PrivateMessageListBase {
    private static final PrivateMessageListBase msgListsBase = new PrivateMessageListBase();
    private final HashMap<String, PrivateMessageList> base = new HashMap<>();

    public static PrivateMessageListBase getInstance() {
        return msgListsBase;
    }

    private PrivateMessageListBase() {
        addUser("user1", new PrivateMessageList());
        addUser("user2", new PrivateMessageList());
        addUser("user3", new PrivateMessageList());
        addUser("user4", new PrivateMessageList());
        addUser("user5", new PrivateMessageList());
        addUser("user6", new PrivateMessageList());
        addUser("user7", new PrivateMessageList());
        addUser("user8", new PrivateMessageList());
        addUser("user9", new PrivateMessageList());
        addUser("user10", new PrivateMessageList());
    }

    public void addUser(String login, PrivateMessageList messageList) {
        base.put(login, messageList);
    }

    public void addMessage(String login, Message msg) {
        base.get(login).add(msg);
    }

    public boolean isLogin(String name) {
        for (String s : base.keySet()) {
            if (s.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public String toJSON(String name, int from) {
        return base.get(name).toJSON(from);
    }
}
