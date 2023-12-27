package model2.mvcboard;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;




public class MVCBoardDAO extends JDBConnect {
	
	public MVCBoardDAO(String drv,String url, String id,String pw) {
		super(drv,url,id,pw);
	}
	
	public MVCBoardDAO(ServletContext application) {
		super(application);
	}
	
	public MVCBoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물의 갯수 반환
	public int selectCount(Map<String,Object> map) {
		int totalCnt =0; //게시물 수
		
		Statement st = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM mvcboard ";
		if(map.get("searchWord")!=null) {
			query+=" WHERE "  +map.get("searchField") + " "
				  +" LIKE '%" +map.get("searchWord")  + "%'";
		}
		
		try {
			st = getCon().createStatement();
			rs = st.executeQuery(query);
			rs.next();
			totalCnt = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 수 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCnt;
	}
	
	public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
		//게시물 목록을 담을 변수
		List<MVCBoardDTO> bbs = new Vector<>();
		MVCBoardDTO dto =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = " SELECT * FROM mvcboard ";
		
			if(map.get("searchWord")!=null) {
				query+=" WHERE "	+map.get("searchField")
					  +" LIKE '%"   +map.get("searchWord") + "%' ";
			}
			
				query += " ORDER BY idx DESC "
					  + " LIMIT ? , ?";//btw inparameter
		
		try {
			ps = getCon().prepareStatement(query);
			//Limit 는 위에 한계선 밑 한계선 이기에 -1,+1을 해줘야함
			int start = (int) map.get("start")-1;
			int end = ((int) map.get("end"))+1;
			ps.setInt(1,start);//start of inparameter
			ps.setInt(2,end);	//end of inparameter
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new MVCBoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				bbs.add(dto);
			}
		
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
}