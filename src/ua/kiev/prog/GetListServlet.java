package ua.kiev.prog;

import ua.kiev.prog.base.MessageListsBase;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListServlet extends HttpServlet {
	
	private MessageListsBase msgList = MessageListsBase.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String room = req.getParameter("room");
		String fromStr = req.getParameter("from");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String json = msgList.toJSON(room, from);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
			byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);
		}

		System.out.println("OUTPUT FOR " + room);
	}
}
