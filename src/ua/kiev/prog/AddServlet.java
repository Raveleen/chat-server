package ua.kiev.prog;

import ua.kiev.prog.base.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet {

    private MessageListsBase msgListBase = MessageListsBase.getInstance();
    private PrivateMessageListBase msgList = PrivateMessageListBase.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if (msg != null) {
            if (msg.getTo() != null) {
                msgList.addMessage(msg.getTo(), msg);
                msgList.addMessage(msg.getFrom(), msg);
                resp.setStatus(200);
            } else {
                msgListBase.addMessage(msg.getRoomName(), msg);
                resp.setStatus(200);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        System.out.println(msg.getRoomName());
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
