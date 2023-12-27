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
	
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;

		String query = "INSERT INTO mvcboard(name,title,content,ofile,sfile,pass,downcount,visitcount) "
								 + " Values (?,?,?,?,?,?,0,0)";
		PreparedStatement ps = null;
		
		try {
			ps = getCon().prepareStatement(query);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getOfile());
			ps.setString(5, dto.getSfile());
			ps.setString(6, dto.getPass());
			
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생 ("+e.getMessage()+")");
			e.printStackTrace();
			return 0;
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public MVCBoardDTO selectView(int idx) {
		MVCBoardDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * "
				+ " FROM mvcboard"
				+ " WHERE idx=?";
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, idx);
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
			}
		
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	//======게시물 조회수 증가=======//
	public void updateVisitCount(int idx) {
		String query = "UPDATE mvcboard "
				+ " SET visitcount = visitcount+1 "
				+ " WHERE idx=?";
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, idx);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("조회수 증가 적용 중 에러 발생 ("+e.getMessage()+")");
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateDownCount(int idx) {
		String query = "UPDATE mvcboard "
				+ " SET downcount = downcount+1 "
				+ " WHERE idx=?";
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, idx);
			ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("다운로드수 증가 적용 중 에러 발생 ("+e.getMessage()+")");
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// Check if input password and the post's password match
	public boolean confirmPassword(String pass, int idx) {
		boolean isCorr = true;
		String query = "SELECT COUNT(*) "
				   + " FROM mvcboard "
				   + " WHERE pass=? AND idx=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(query);
			ps.setString(1, pass);
			ps.setInt(2, idx);
			rs = ps.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}

		}catch(Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}
	
	//Delete Post
	public int deletePost(int idx) {
		int result = 0;
		String query = "DELETE FROM mvcboard "
					 + " WHERE idx=?";
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, idx);
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("삭제 중 에러 발생 ("+e.getMessage()+")");
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void updatePost(MVCBoardDTO dto) {
		int result = 0;
		String query = "UPDATE mvcboard "
				 	 + " SET title=?,name=?,content=?,ofile=?,sfile=? "
					 + " WHERE idx=? and pass=?";
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(query);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getOfile());
			ps.setString(5, dto.getSfile());
			ps.setInt(6, dto.getIdx());
			ps.setString(7, dto.getPass());
			result = ps.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("게시물 수정 중 에러 발생 ("+e.getMessage()+")");
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}