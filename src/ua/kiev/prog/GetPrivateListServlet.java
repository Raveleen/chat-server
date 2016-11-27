package ua.kiev.prog;

import ua.kiev.prog.base.PrivateMessageList;
import ua.kiev.prog.base.PrivateMessageListBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetPrivateListServlet extends HttpServlet {
    private PrivateMessageListBase msgList = PrivateMessageListBase.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String fromStr = req.getParameter("from");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String json = msgList.toJSON(login, from);
        if (json != null) {
            resp.setStatus(200);
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        } else {
            resp.setStatus(404);
        }

        System.out.println("OUTPUT FOR " + login);
    }
}
