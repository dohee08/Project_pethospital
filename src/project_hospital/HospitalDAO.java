package project_hospital;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.conn.DBConn;

public class HospitalDAO extends DBConn {
	/** * 전체조회 */
	public ArrayList<UserVO> select(){
			
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select bno, bkind, byear, bmonth, bday, btime "
					+ "btext, bsymptom, bid from booking";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
//				vo.setBno(rs.getString(1));
//				vo.setBkind(rs.getString(2));
//				vo.setByear(rs.getString(3));
//				vo.setBmonth(rs.getString(4));
//				vo.setBday(rs.getString(5));
//				vo.setBtime(rs.getString(6));
//				vo.setBtext(rs.getString(7));
//				vo.setBsymptom(rs.getString(8));
//				vo.setBday(rs.getString(9));

				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
		
	/** 멤버 전체 조회 */
	public ArrayList<UserVO> memberSelect(){
			
		ArrayList<UserVO> memlist = new ArrayList<UserVO>();
		
		try {
			String sql ="select mid, mpass, mphone, mname, mkind from member";
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setMid(rs.getString(1));
				vo.setMpass(rs.getString(2));
				vo.setMphone(rs.getString(3));
				vo.setMname(rs.getString(4));
				vo.setMkind(rs.getString(5));
				
				memlist.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return memlist;
	}//select

	/** 예약번호 검색 */
		public int search(String bno) {
			int result = 0;
		
			try {
				String sql = "select count(*) from booking where bno = ? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, bno);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		/** 수정 */
		public boolean update(UserVO vo) {
			boolean result = false;
			
			try {
				
				String sql="update booking set rkind = ? , rname = ?, rdate =? , rtime =? where rno =?";
				getPreparedStatement(sql);
				
//				pstmt.setString(1, vo.getRkind());
//				pstmt.setString(2, vo.getRname());
//				pstmt.setString(3, vo.getRdate());
//				pstmt.setString(4, vo.getRtime());
//				pstmt.setString(5, vo.getRno());
				
				System.out.println(pstmt.executeUpdate());
				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		
		/** 예약정보 조회 */
		public UserVO select(String rno) {
			UserVO vo = new UserVO();
			try {
				String sql = "select rkind, rname, rdate,rtime from booking where rno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, rno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
//					vo.setRkind(rs.getString(1));
//					vo.setRname(rs.getString(2));
//					vo.setRdate(rs.getString(3));
//					vo.setRtime(rs.getString(4));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
	/** 회원 로그인 */
	public boolean memlogin(String id, String pass) {
		boolean result = false;
		
		try {
			String sql = "SELECT COUNT(MID) FROM MEMBER WHERE MID=? AND MPASS=? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count=rs.getInt(1);
			}
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
		
	/** 매니저 로그인 */
	public boolean manlogin(String id, String pass) {
		boolean result = false;
			
		try {
			String sql = "select count(sid) from manager where sid=? and spass=? "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
				
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) 
					result = true;
		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
		
	/** 회원가입 **/
	public boolean register(UserVO vo) {
		boolean result = false;
		
		try {
				//아이디가 있을경우
		//아이디가 없을경우
			String sql = "insert into member values(?,?,?,?,?) "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpass());
			pstmt.setString(3, vo.getMphone());
			pstmt.setString(4, vo.getMname());
			pstmt.setString(5, vo.getMkind());
					
			pstmt.executeUpdate();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** delSelect(String mid) **/
	public boolean delSelect(String mid) {
		boolean result = false;
		
		try {
			String sql = "SELECT COUNT(*) FROM MEMBER WHERE MID=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** delete(String mid) **/
	public boolean delete(String mid) {
		boolean result = false;
		
		try {
			String sql = "DELETE FROM MEMBER WHERE MID=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
			
}
