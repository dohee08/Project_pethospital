package project_hospital;

import java.util.ArrayList;

import db.conn.DBConn;



public class HospitalDAO extends DBConn {
	/** * 전체조회 */
	public ArrayList<UserVO> select(){
			
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select rno, rkind, rname, rdate, rtime from booking";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setRno(rs.getString(1));
				vo.setRkind(rs.getString(2));
				vo.setRname(rs.getString(3));
				vo.setRdate(rs.getString(4));
				vo.setRtime(rs.getString(5));

				
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
		

	/** 예약번호 검색 */
		public int search(String rno) {
			int result = 0;
		
			try {
				String sql = "select count(*) from booking where rno = ? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, rno);
				
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
				
				pstmt.setString(1, vo.getRkind());
				pstmt.setString(2, vo.getRname());
				pstmt.setString(3, vo.getRdate());
				pstmt.setString(4, vo.getRtime());
				pstmt.setString(5, vo.getRno());
				
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
					vo.setRkind(rs.getString(1));
					vo.setRname(rs.getString(2));
					vo.setRdate(rs.getString(3));
					vo.setRtime(rs.getString(4));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/** 로그인 */
		public boolean login(String id, String pass) {
			boolean result = false;
			
			try {
				String sql = "select count(id) from users where id =? and pass = ?";
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
}
