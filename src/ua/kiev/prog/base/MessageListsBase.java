package ua.kiev.prog.base;

import java.util.HashMap;

public class MessageListsBase {
    private static final MessageListsBase msgListsBase = new MessageListsBase();
    private final HashMap<String, MessageList> base = new HashMap<>();

    public static MessageListsBase getInstance() {
        return msgListsBase;
    }

    private MessageListsBase() {
        base.put("main", new MessageList());
        base.put("private", new MessageList());
    }

    public void addRoom(String name, MessageList messageList) {
        base.put(name, messageList);
    }

    public void addMessage(String roomname, Message msg) {
        base.get(roomname).add(msg);
    }

    public boolean isRoom(String name) {
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
