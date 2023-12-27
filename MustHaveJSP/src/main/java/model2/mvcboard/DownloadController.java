package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get Parameters
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		int idx = 0;
		String sidx = req.getParameter("idx");
		if(sidx!=null) idx = Integer.parseInt(sidx);
		
		//File Download
		FileUtil.downloadFile(req, resp, "/Uploads", sfile, ofile);
		
		//Downloadcount increase
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.updateDownCount(idx);
		dao.close();
	}
}
