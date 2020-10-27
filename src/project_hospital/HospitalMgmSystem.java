package project_hospital;

import java.util.ArrayList;


public class HospitalMgmSystem {
	//field
		HospitalDAO dao = new HospitalDAO();
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		//Method
		/** 미용등록 */
		public boolean hairCut(UserVO vo) {
			return dao.insert(vo);
		}
		
		/** 병원 예약 등록 **/
		public boolean Hospital_reserve(UserVO vo) {
			return dao.Hospital_reserve(vo);
		}
		
		/** 병원전체 리스트 출력 */
		public ArrayList<UserVO> hselectList(String id){
			return dao.hselectall(id);
		}
		
		/** 미용전체 리스트 출력 */
		public ArrayList<UserVO> sselectList(String id){
			return dao.sselectall(id);
		}
		
		/** 병원예약정보 찾기 */
		public int hsearchhno (String hno) {
			int idx = 0;
			idx = dao.hsearch(hno);
			
			return idx;
		}
		
		/** 병원예약정보 찾기 */
		public int ssearchsno (String sno) {
			int idx = 0;
			idx = dao.ssearch(sno);
			
			return idx;
		}
		
		/** 병원예약정보 수정 */
		public boolean update(UserVO vo) {
			boolean result = false;
			
			result = dao.hupdate(vo);
			
			return result;
		}
		
		/** 미용예약정보 수정 */
		public boolean supdate(UserVO vo) {
			boolean result = false;
			
			result = dao.supdate(vo);
			
			return result;
		}
		
		/** 병원예약정보 찾기*/
		public UserVO hselectUser(String hno) {
			UserVO vo = null;
			
			vo = dao.hselect(hno);
			return vo;
		}
		
		/** 미용예약정보 찾기*/
		public UserVO sselectUser(String sno) {
			UserVO vo = null;
			
			vo = dao.sselect(sno);
			return vo;
		}
		
		/** 미용예약 삭제 
		 * *
		 */
		public boolean sdelete(String sno) {
			boolean result = false;
			
			result = dao.sdelete(sno);
			
			return result;
		}
		
		/** 병원예약 삭제 
		 * *
		 */
		public boolean hdelete(String hno) {
			boolean result = false;
			
			result = dao.hdelete(hno);
			
			return result;
		}
		
		/** 내정보 찾기 */
		public UserVO selectMember(String mid) {
			UserVO vo = null;
			
			vo = dao.mselect(mid);
			return vo;
		}
		
		/** 내정보 수정 
		 */
		public boolean memberupdate(UserVO vo) {
			boolean result = false;
			
			result = dao.memberupdate(vo);
			
			return result;
		}
		
		
		/** 로그인 */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
		}
		
}
