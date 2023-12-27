package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", req.getParameter("mode"));
		req.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get Parameters
		MVCBoardDAO dao = null;
		MVCBoardDTO dto = null;
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		int idx = 0;
		String sidx = req.getParameter("idx");
		if(sidx!=null) idx = Integer.parseInt(sidx);
	
		//Check pwd
		dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx);
		dao.close();
		
		if(confirmed) {// pwd matches
			if(mode.equals("edit")) { //Edit mode
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass); //save pwd
				resp.sendRedirect("../mvcboard/edit.do?idx="+idx); // go back to edit
			}
			else if(mode.equals("delete")) {//Delete mode 
				dao = new MVCBoardDAO();
				dto = dao.selectView(idx); //Save post before delete
				int result = dao.deletePost(idx);
				dao.close();
				if(result==1) { //delete file if post delete succeeded
					String saveFileName = dto.getSfile();
					FileUtil.deleteFile(req, "/Uploads", saveFileName);
				}
				JSFunction.alertLocation(resp, "삭제되었습니다.","../mvcboard/list.do");
			}
		}
		else {		// pwd do not match
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
	}
}
