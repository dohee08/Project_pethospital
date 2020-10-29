package project_hospital;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.conn.DBConn;

public class HospitalDAO extends DBConn {
	/*
	 * �̿���
	 */
	public boolean insert(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into SALONRES values(?,?,?,?,?,?,?,?)";
			
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getSno());
			pstmt.setString(2, vo.getSyear());
			pstmt.setString(3, vo.getSmonth());
			pstmt.setString(4, vo.getSday());
			pstmt.setString(5, vo.getStime());
			pstmt.setString(6, vo.getSgender());
			pstmt.setString(7, vo.getStext());
			pstmt.setString(8, vo.getSmid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 
	 * ���� ���� ��� 
	 */
	public boolean Hospital_reserve(UserVO vo) {
		boolean result = false;
		
		try {
			
			String sql = "insert into hbooking values(?,?,?,?,?,?,?)";
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getHno());
			pstmt.setString(2, vo.getHsymptom());
			pstmt.setString(3, vo.getHyear());
			pstmt.setString(4, vo.getHmonth());
			pstmt.setString(5, vo.getHday());
			pstmt.setString(6, vo.getHtime());
			pstmt.setString(7, vo.getHmid());
			
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** * ��ü��ȸ ������
	 * */
	public ArrayList<UserVO> hselectall (String id){
			
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select hno, mname, hyear,hmonth,hday , htime from hbooking h, member m where h.mid = m.mid and h.mid = ?";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setHno(rs.getString(1));
				vo.setMname(rs.getString(2));
				vo.setHyear(rs.getString(3));
				vo.setHmonth(rs.getString(4));
				vo.setHday(rs.getString(5));
				vo.setHtime(rs.getString(6));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
	/*
	 * �̿� Ÿ�� üũ
	 */
	public ArrayList<String> timeck(String y,String m,String d){
		ArrayList<String> tlist = new ArrayList<String>();
		try {
			String sql ="SELECT * FROM TIMECHECK" + 
					" WHERE TIME NOT IN (SELECT STIME FROM SALONRES " + 
					" WHERE SYEAR = ? AND SMONTH = ? AND SDAY = ?)" + 
					" ORDER BY TIME";
			
			//System.out.println("1111");
			getPreparedStatement(sql);
			//System.out.println("2222");
			pstmt.setString(1, y);
			pstmt.setString(2, m);
			pstmt.setString(3, d);
			rs=pstmt.executeQuery();
			//System.out.println("3333");
			while(rs.next()) {
				tlist.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return tlist;
	}
	/** ��ü��ȸ �̿��� 
	 *
	 */
	public ArrayList<UserVO> sselectall (String id){
		
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select sno, mname, syear, smonth,sday , stime from SALONRES s, member m where s.mid = m.mid and s.mid = ?";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setSno(rs.getString(1));
				vo.setMname(rs.getString(2));
				vo.setSyear(rs.getString(3));
				vo.setSmonth(rs.getString(4));
				vo.setSday(rs.getString(5));
				vo.setStime(rs.getString(6));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
		
	/** �Ŵ��� - ��� ��ü ��ȸ **/
	public ArrayList<UserVO> memberSelect(){
			
		ArrayList<UserVO> memlist = new ArrayList<UserVO>();
		
		try {
			String sql ="select rownum mno, mid, mpass, mphone, mname, mkind from member order by sysdate ";
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setMno(rs.getString(1));
				vo.setMid(rs.getString(2));
				vo.setMpass(rs.getString(3));
				vo.setMphone(rs.getString(4));
				vo.setMname(rs.getString(5));
				vo.setMkind(rs.getString(6));
				
				memlist.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return memlist;
	}//select

	/** ���������ȣ �˻�
	 *  */
		public int hsearch(String hno) {

			int result = 0;
		
			try {
				String sql = "select count(*) from hbooking where hno = ? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, hno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		
		/** �̿뿹���ȣ �˻�
		 *  */
			public int ssearch(String sno) {
				int result = 0;
			
				try {
					String sql = "select count(*) from SALONRES where sno = ? ";
					getPreparedStatement(sql);
					
					pstmt.setString(1, sno);
					
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						result = rs.getInt(1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}
		
		/** ��������ð� ��ġ���� Ȯ��
		 * 
		 */
		public int hcheck(UserVO vo) {
			int result = 0;
			try {
				String sql1="select count(*) from hbooking where hyear =? and hmonth =? and hday = ? and htime =?";
				getPreparedStatement(sql1);
				pstmt.setString(1, vo.getHyear());
				pstmt.setString(2, vo.getHmonth());
				pstmt.setString(3, vo.getHday());
				pstmt.setString(4, vo.getHtime());
				
				rs= pstmt.executeQuery();
				
				
				if(rs.next()) {result = rs.getInt(1);}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		
		/** �̿뿹��ð� ��ġ���� Ȯ��
		 * 
		 */
		public int scheck(UserVO vo) {
			int result = 0;
			try {
				String sql1="select count(*) from SALONRES where syear =? and smonth =? and sday = ? and stime =?";
				getPreparedStatement(sql1);
				pstmt.setString(1, vo.getSyear());
				pstmt.setString(2, vo.getSmonth());
				pstmt.setString(3, vo.getSday());
				pstmt.setString(4, vo.getStime());
				
				rs= pstmt.executeQuery();
				
				
				if(rs.next()) {result = rs.getInt(1);}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
			
		/** ���� ����
		 *  */
		public boolean hupdate(UserVO vo) {
			boolean result = false;
			try {
			
				String sql2="update hbooking set hyear =? ,hmonth =? , hday =?, htime =? where hno =?";
				getPreparedStatement(sql2);
				
				pstmt.setString(1, vo.getHyear());
				pstmt.setString(2, vo.getHmonth());
				pstmt.setString(3, vo.getHday());
				pstmt.setString(4, vo.getHtime());
				pstmt.setString(5, vo.getHno());

				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** �̿����
		 *  */
		public boolean supdate(UserVO vo) {
			boolean result = false;
			
			try {
				
				String sql="update SALONRES set syear =? ,smonth =? , sday =?, stime =? where sno =?";
				getPreparedStatement(sql);
				
			
				pstmt.setString(1, vo.getSyear());
				pstmt.setString(2, vo.getSmonth());
				pstmt.setString(3, vo.getSday());
				pstmt.setString(4, vo.getStime());
				pstmt.setString(5, vo.getSno());

				
				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** ���� �������� ��ȸ
		 *  */
		public UserVO hselect(String hno) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mname, hyear,hmonth,hday from hbooking h ,member m where m.mid = h.mid and hno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, hno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMname(rs.getString(1));
					vo.setHyear(rs.getString(2));
					vo.setHmonth(rs.getString(3));
					vo.setHday(rs.getString(4));
				}
				
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/** �̿����� ��ȸ
		 *  */
		public UserVO sselect(String sno) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mname, syear,smonth,sday from SALONRES s ,member m where m.mid = s.mid and sno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, sno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMname(rs.getString(1));
					vo.setSyear(rs.getString(2));
					vo.setSmonth(rs.getString(3));
					vo.setSday(rs.getString(4));
				}
				
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/** ������� �������� 
		 * */
		public boolean hdelete(String hno) {
			boolean result = false;
			
			try {
				String sql = "delete from hbooking where hno = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, hno);
				int count = pstmt.executeUpdate();
				
				if(count!=0) result =true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}


		/** ������� �̿뿹��
		 *
		 **/
		public boolean sdelete(String sno) {
			boolean result = false;
			
			try {
				String sql = "delete from SALONRES where sno = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, sno);
				int count = pstmt.executeUpdate();
				
				if(count!=0) result =true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		/** ������ �������� ������������ Ȯ�� 
		 * */
		public int lastcheckh(String mid) {
			int result = 0;
			
			try {
				String sql = "select count(*) from hbooking h , member m where h.mid = m.mid and h.mid = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, mid);
				
				rs = pstmt.executeQuery();
				if(rs.next()) result = rs.getInt(1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		
		/** ������ �������� �̿뿹������ Ȯ�� 
		 * */
		public int lastchecks(String mid) {
			int result = 0;
			
			try {
				String sql = "select count(*) from SALONRES s , member m where s.mid = m.mid and s.mid = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, mid);
				
				rs = pstmt.executeQuery();
				if(rs.next()) result = rs.getInt(1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		/**������ ��ȸ
		 * */
		public UserVO mselect(String mid) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mid,mpass,mphone,mname,mkind from member where mid = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMid(rs.getString(1));
					vo.setMpass(rs.getString(2));
					vo.setMphone(rs.getString(3));
					vo.setMname(rs.getString(4));
					vo.setMkind(rs.getString(5));	
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return vo;
		}
		
		/** ������ ����
		 */
		public boolean memberupdate(UserVO vo) {
			boolean result = false;
			
			try {
				String sql = "update member set mid = ?, mpass=? ,mphone=?, mname =? , mkind =? where mid =? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, vo.getMid());
				pstmt.setString(2, vo.getMpass());
				pstmt.setString(3, vo.getMphone());
				pstmt.setString(4, vo.getMname());
				pstmt.setString(5, vo.getMkind());
				pstmt.setString(6, vo.getMid());
				

				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** �α��� 
		 * */
		public boolean login(String id, String pass) {
			boolean result = false;
			
			try {
				String sql = "select count(mid) from member where mid =? and mpass = ?";
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
		
	/** ȸ�� �α��� -- ������ �� **/
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
				
			
	/** �Ŵ��� �α��� -- ������ ��*/
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
		
	/** ȸ������ -- ������ ��**/
	public boolean register(UserVO vo) {
		boolean result = false;
		
		try {
		//���̵� �������
		//���̵� �������
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
	
	/** �Ŵ��� - ���� ��� id�˻� **/
	public int searchMid(String mid) {
		int result = 0;
		
		try {
			String sql = "select count(*) from member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** �Ŵ��� - ���� ��� ���� ��� **/
	public UserVO selectMemInfo(String mid) {
		UserVO vo = new UserVO();
		
		try {
			String sql = "select mpass, mphone, mname, mkind from member where mid=?";
			getPreparedStatement(sql);
			
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setMpass(rs.getString(1));
				vo.setMphone(rs.getString(2));
				vo.setMname(rs.getString(3));
				vo.setMkind(rs.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/** �Ŵ��� - ��� ���� �Ϸ� **/
	public boolean menUpdate(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "update member set mpass=?, mphone=?, mname=?, mkind=?"
						+ "where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getMpass());
			pstmt.setString(2, vo.getMphone());
			pstmt.setString(3, vo.getMname());
			pstmt.setString(4, vo.getMkind());
			pstmt.setString(5, vo.getMid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
