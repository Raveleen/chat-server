package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.base.Message;
import ua.kiev.prog.base.UserBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = requestBodyToArray(req);
        String s = new String(buf, StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder().create();
        String[] result = gson.fromJson(s, String.class).split(";");

        if (UserBase.getInstance().checkPassword(result[0], result[1])) {

            /*HttpSession session = req.getSession(true);
            session.setAttribute("login", result[0]);

            session.setAttribute("roomname", "main");
            resp.sendError(200);
            */
            UserBase.getInstance().changeStatus(result[0], "online");
            resp.setStatus(200);
        } else {
            resp.sendError(401);
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
