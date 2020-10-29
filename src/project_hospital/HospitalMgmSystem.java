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
		/** 미용등록 */
		public boolean hairCut(UserVO vo) {
			return dao.insert(vo);
		}
		
		/** 병원 예약 등록 **/
		public boolean Hospital_reserve(UserVO vo) {
			return dao.Hospital_reserve(vo);
		}
		
		/** 병원 예약 시간 중복 체크 **/ 
		public int reserveTimeCheck(UserVO vo) {
			return dao.hcheck(vo);
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
		
		/** 수정전에  병원시간 체크 */
		public int hcheck(UserVO vo) {
			int result = 0;
			
			result = dao.hcheck(vo);
			
			return result;
		}
		
		/** 수정전에  미용시간 체크 */
		public int scheck(UserVO vo) {
			int result = 0;
			
			result = dao.scheck(vo);
			
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
		
		/** 내정보 삭제전 병원예약 확인 
		 * 
		 */
		public int lastcheckh(String mid) {
			int result = 0;
			
			result = dao.lastcheckh(mid);
			
			return result;
		}
		
		
		/** 내정보 삭제전 미용예약 확인 
		 * 
		 */
		public int lastchecks(String mid) {
			int result = 0;
			
			result = dao.lastchecks(mid);
			
			return result;
		}
		
		/** 로그인 */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
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
		
		/** 매니저 - 전체 멤버 리스트 출력 */
		public ArrayList<UserVO> memberSelectList(){
			return dao.memberSelect();
		}
		
		/** 매니저 - 수정 검색 **/
		public int searchMid(String mid) {
			return dao.searchMid(mid);
		}
		
		/** 매니저 - 수정 멤버 정보 출력 **/
		public UserVO selectMemInfo(String mid) {
			return dao.selectMemInfo(mid);
		}
		
		/** 매니저 - 멤버 수정 완료 **/
		public boolean menUpdate(UserVO vo) {
			return dao.menUpdate(vo);
		}
		
		/** 매니저 - 삭제 검색**/
		public boolean deleteSearch(String mid) {
			return dao.delSelect(mid);
		}
		
		/** 매니저 - 삭제 **/
		public boolean delete(String mid) {
			return dao.delete(mid);
		}
		
}
