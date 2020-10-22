package project_hospital;

import java.util.ArrayList;

public class HospitalMgmSystem {
	//field
		HospitalDAO dao = new HospitalDAO();
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		//Method
		
		/** 전체 리스트 출력 */
		public ArrayList<UserVO> selectList(){
			return dao.select();
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
		
		/** 로그인 */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
		}
}
