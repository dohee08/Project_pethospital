package project_hospital;

import java.util.ArrayList;

public class HospitalMgmSystem {
	//field
		HospitalDAO dao;
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		
		//Constructor
		public HospitalMgmSystem() {
			dao = new HospitalDAO();
		}
		
		//Method
		
		/** 전체 리스트 출력 */
		public ArrayList<UserVO> selectList(){
			return dao.select();
		}
		
		/** 전체 멤버 리스트 출력 */
		public ArrayList<UserVO> memberSelectList(){
			return dao.memberSelect();
		}
		
		/** 예약정보 찾기 */
		public int searchRno (String rno) {
			int idx = 0;
			idx = dao.search(rno);
			
			return idx;
		}
		
		/** 예약정보 수정 */
		public boolean update(UserVO vo) {
			boolean result = false;
			
			result = dao.update(vo);
			
			return result;
		}
		
		/** 예약정보 수정 */
		public UserVO selectUser(String rno) {
			UserVO vo = null;
			
			vo = dao.select(rno);
			return vo;
		}
		
		/** 회원 로그인 */
		public boolean memlogin(String id ,String pass) {
			return dao.memlogin(id,pass);
		}
		
		/** 매니저 로그인 */
		public boolean manlogin(String id ,String pass) {
			return dao.manlogin(id,pass);
		}
		
		/** 회원가입 **/
		public boolean memRegister(UserVO vo) {
			return dao.register(vo);
		}
		
		/** 검색 - 삭제**/
		public boolean deleteSearch(String mid) {
			return dao.delSelect(mid);
		}
		
		/** 삭제 **/
		public boolean delete(String mid) {
			return dao.delete(mid);
		}
}
