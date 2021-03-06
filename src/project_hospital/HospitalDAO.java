package project_hospital;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.conn.DBConn;

public class HospitalDAO extends DBConn {
	/*
	 * 미용등록
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
	 * 병원 예약 등록 
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
	
	/** * 전체조회 병원편
	 * */
	public ArrayList<UserVO> hselectall (String id){
			
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select hno, mname, hyear,hmonth,hday , htime from hbooking h, member m where h.mid = m.mid and h.mid = ? order by hyear,hmonth,hday,htime asc";
			
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

	/** 전체조회 미용편 
	 *
	 */
	public ArrayList<UserVO> sselectall (String id){
		
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select sno, mname, syear, smonth,sday , stime from SALONRES s, member m where s.mid = m.mid and s.mid = ? order by syear,smonth,sday,stime asc";
			
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
		
	/** 매니저 - 멤버 전체 조회 **/
	public ArrayList<UserVO> memberSelect(){
			
		ArrayList<UserVO> memlist = new ArrayList<UserVO>();
		
		try {
			String sql ="select rownum mno, mid, mpass, mphone, mname, mkind from member ";
			
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

	/** 병원예약번호 검색
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
		
		
		/** 미용예약번호 검색
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
		
		/** 병원예약시간 겹치는지 확인
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
		
		
		/** 미용예약시간 겹치는지 확인
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
			
		/** 병원 수정
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
		
		/** 미용수정
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
		
		/** 병원 예약정보 조회
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
		
		/** 미용정보 조회
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
		
		/** 예약삭제 병원예약 
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


		/** 예약삭제 미용예약
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
		
		/** 내정보 삭제전에 병원예약정보 확인 
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
		
		
		/** 내정보 삭제전에 미용예약정보 확인 
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
		
		/**내정보 조회
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
		
		/** 내정보 수정
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
		
		/** 로그인 
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
		
		
		/** 게시판 내용 불러오기  
		 * */
	public ArrayList<UserVO> gettext() {
		ArrayList<UserVO> text = new ArrayList<UserVO>();
		
		try {
			String sql ="select rownum rno,pno, pname, ptitle, ptext, pdate from (select pno, pname, ptitle, ptext, pdate from post order by pdate asc)";
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			
			while(rs.next()) {
				UserVO vo = new UserVO();
		
				vo.setRno(rs.getString(1));
				vo.setPno(rs.getString(2));
				vo.setPname(rs.getString(3));
				vo.setPtitle(rs.getString(4));
				vo.setPtext(rs.getString(5));
				vo.setPdate(rs.getString(6));
			
				
				text.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return text;
	}
	
	/** 이름 가져오기 */
	public String rename(String id) {
		String result="";
		
		try {
			String sql ="select mname from member where mid =?";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) result = rs.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 매니저 이름 가져오기 */
	public String renameMan(String sid) {
		String result="";
		
		try {
			String sql ="select sname from manager where sid =?";
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) result = rs.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/** 매니저 아이디 가져오기 */
	public String getManaId(String sid) {
		String result="";
		
		try {
			String sql ="select sid from manager where sname =?";
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) result = rs.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 게시판 내용 업로드 */
	public boolean pinsert(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into post (pno,pname,ptitle,ptext,pdate,mid) values(?,?,?,?,?,?)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getPno());
			pstmt.setString(2, vo.getPname());
			pstmt.setString(3, vo.getPtitle());
			pstmt.setString(4, vo.getPtext());
			pstmt.setString(5, vo.getPdate());
			pstmt.setString(6, vo.getMid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** 게시판 - 삭제 검색 **/
	public boolean deletePostSearch(String pno) {
		boolean result = false;
		
		try {
			String sql = "SELECT COUNT(*) FROM POST WHERE PNO=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** 게시판- 삭제 진행  **/
	public boolean deletePost(String pno ,String name) {
		boolean result = false;
		
		try {
			String sql = "DELETE FROM POST WHERE PNO=? and pname = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, pno);
			pstmt.setString(2, name);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 1대1 게시판 내용 가져오기 **/
	public ArrayList<UserVO> receive(String id) {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
	
		try {
			String sql = "select rownum rno, r.aid ,atitle,atext ,adate,asname, btitle from receive r ,send s where r.bid =s.bid and r.mid = ?";
			
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				UserVO vo = new UserVO();
		
				vo.setRno(rs.getString(1));
				vo.setAid(rs.getString(2));
				vo.setAtitle(rs.getString(3));
				vo.setAtext(rs.getString(4));
				vo.setAdate(rs.getString(5));
				vo.setAsname(rs.getString(6));
				vo.setBid(rs.getString(7));
						
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/** 1:1 문의 - send 테이블 정보 불러오기  (원장님 이름 가져오기)**/
	public ArrayList<UserVO> send(String id) {
		ArrayList<UserVO> send = new ArrayList<UserVO>();
		try {
			String sql ="select rownum rno, bid, btitle, btext, bsname, bmname,bdate from send where mid = ? ";
			
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
		
				vo.setRno(rs.getString(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBtext(rs.getString(4));
				vo.setBsname(rs.getString(5));
				vo.setBmname(rs.getString(6));
				vo.setBdate(rs.getString(7));
				
				send.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return send;
	}
	
	
	/** 1:1 문의 - send 테이블 정보 불러오기 **/
	public ArrayList<UserVO> getSendInfo(String id) {
		ArrayList<UserVO> send = new ArrayList<UserVO>();
		String sid = id;
		try {
			String sql ="select rownum rno, bid, btitle, btext, bmname, bsname, bdate from send where sid = ? ";
			
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
		
				vo.setRno(rs.getString(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBtext(rs.getString(4));
				vo.setBmname(rs.getString(5));
				vo.setBsname(rs.getString(6));
				vo.setBdate(rs.getString(7));
				
				send.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return send;
	}
	
	/** 1:1 문의 - receive 테이블 정보 불러오기 **/
	public ArrayList<UserVO> getReceiveInfo(String id) {
		ArrayList<UserVO> receive = new ArrayList<UserVO>();
		String sid = id;
		try {
			String sql ="select rownum rno, aid, atitle, atext, asname, amname, adate from receive where sid = ? ";
			
			getPreparedStatement(sql);
			pstmt.setString(1, sid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
		
				vo.setRno(rs.getString(1));
				vo.setAid(rs.getString(2));
				vo.setAtitle(rs.getString(3));
				vo.setAtext(rs.getString(4));
				vo.setAsname(rs.getString(5));
				vo.setAmname(rs.getString(6));
				vo.setAdate(rs.getString(7));
				
				receive.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		 return receive;
	}
	
	/** 매니저 - 멤버이름 가져오기 */
	public String getMid(String Bmname) {
		String result="";
		
		try {
			String sql ="select mid from send where bmname =?";
			getPreparedStatement(sql);
			pstmt.setString(1, Bmname);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) result = rs.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**  멤버이름 가져오기 */
	public String gettMid(String name) {
		String result="";
		
		try {
			String sql ="select mid from member where mname =?";
			getPreparedStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) result = rs.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 1:1문의 - 답변 내용 업로드 */
	public boolean ainsert(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into receive (aid,atitle,atext,adate,amname,asname,mid,sid,bid) values(?,?,?,?,?,?,?,?,?)";
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getAid());
			pstmt.setString(2, vo.getAtitle());
			pstmt.setString(3, vo.getAtext());
			pstmt.setString(4, vo.getAdate());
			pstmt.setString(5, vo.getAmname());
			pstmt.setString(6, vo.getAsname());
			pstmt.setString(7, vo.getMid());
			pstmt.setString(8, vo.getSid());
			pstmt.setString(9, vo.getBid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/*
	 * 1:1 문의 등록
	 */
	public boolean sinsert(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into send values(?,?,?,?,?,?,?,?)";
			
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBid());
			pstmt.setString(2, vo.getBtitle());
			pstmt.setString(3, vo.getBtext());
			pstmt.setString(4, vo.getMid());
			pstmt.setString(5, vo.getSid());
			pstmt.setString(6, vo.getBsname());
			pstmt.setString(7, vo.getBdate());
			pstmt.setString(8, vo.getBmname());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 회원 로그인 -- 시작할 때 **/
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
				
			
	/** 매니저 로그인 -- 시작할 때*/
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
		
	/** 회원가입 -- 시작할 때**/
	public boolean register(UserVO vo) {
		boolean result = false;
		
		try {
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
			String sql = "DELETE FROM MEMBER casecade WHERE MID=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** 매니저 - 수정 멤버 id검색 **/
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
	
	/** 매니저 - 수정 멤버 정보 출력 **/
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
	
	/** 매니저 - 멤버 수정 완료 **/
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
