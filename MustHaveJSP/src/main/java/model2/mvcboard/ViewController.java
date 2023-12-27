package model2.mvcboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Bring Board
		MVCBoardDAO dao = null;
		MVCBoardDTO dto = null;
		int idx = 0;
		String sidx = req.getParameter("idx");
		if(sidx!=null) idx = Integer.parseInt(sidx);
		
		dao = new MVCBoardDAO();
		dao.updateVisitCount(idx);
		dto = dao.selectView(idx);
		dao.close();
		
		//Change row
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		//Get extension
		String ext = null, fileName = dto.getSfile();
		if(fileName!=null) ext = fileName.substring(fileName.lastIndexOf(".")+1);
		
		//Check if image
		String[] mimeStr = {"png","jpg","gif"};
		List<String> mimeList = Arrays.asList(mimeStr); //fixed list as mimeStr
		boolean isImage = false;
		if(mimeList.contains(ext)) isImage = true;
		
		//전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		req.setAttribute("dto", dto);
		req.setAttribute("isImage", isImage);
		req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp);
		
	}
}
