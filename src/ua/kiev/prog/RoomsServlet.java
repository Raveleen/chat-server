package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.base.MessageList;
import ua.kiev.prog.base.MessageListsBase;
import ua.kiev.prog.base.UserBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RoomsServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = requestBodyToArray(req);
        String s = new String(buf, StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder().create();
        String[] result = gson.fromJson(s, String.class).split(";");

        if (result[0].equals("create")) {
            MessageListsBase.getInstance().addRoom(result[1], new MessageList());
            resp.setStatus(200);
        } else if (result[0].equals("enter")) {
            if (MessageListsBase.getInstance().isRoom(result[1])) {
                UserBase.getInstance().changeRoom(result[1], result[2]);
                resp.setStatus(200);
            }
        } else {
            resp.setStatus(401);
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
