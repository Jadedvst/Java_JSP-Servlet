package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/12Servlet/DirectServletPrint.do"): web-xml에 입력시 필요없음
public class DirectServletPrint extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/* GET을 사용시 이곳에서 바로 요청해서 출력가능
	 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {  }
	 */
	
	/* SERVICE을 사용시 이곳에서 바로 요청해서 출력가능(req,resp)둘다 가능
	 * @Override protected void service(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {  }
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head><title>DirectServletPrint</title></head>");
		writer.println("<body>");
		writer.println("<h2>서블릿에서 직접 출력</h2>");
		writer.println("<p>jsp로 포워드 하지 않습니다.</p>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
}
