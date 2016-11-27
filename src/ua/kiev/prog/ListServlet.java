package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.base.UserBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(UserBase.getInstance().toString(), String.class);

        if (json != null) {
            OutputStream outputStream = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            outputStream.write(buf);
        }
    }
}
