package ua.kiev.prog.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class PrivateMessageList {
    private static final int LIMIT = 100;
    private int firstIndex = 0;
    private int lastIndex = 0;

    private final Gson gson;

    private final Map<Integer, Message> list = new HashMap<>();

    public PrivateMessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(firstIndex);
            firstIndex++;
        }

        list.put(lastIndex++, m);
    }

    public synchronized String toJSON(int n) {
        if (n == list.size()) return null;
        return gson.toJson(new JsonMessages(list, n, lastIndex));
    }
}
