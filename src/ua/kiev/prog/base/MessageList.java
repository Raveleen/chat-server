package ua.kiev.prog.base;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
    private static final int LIMIT = 100;
    private int firstIndex = 0;
    private int lastIndex = 0;

    private final Gson gson;

    private final Map<Integer, Message> list = new HashMap<>();

    public MessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(firstIndex);
            firstIndex++;
        }

        list.put(++lastIndex, m);
    }

    public synchronized String toJSON(int n) {
        if (n == list.size()) return null;
        return gson.toJson(new JsonMessages(list, n, lastIndex));
    }
}
