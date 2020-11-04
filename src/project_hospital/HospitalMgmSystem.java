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
		/*
		 * 미용 예약 시간 중복 체크
		 */
		public int salonTimeCheck(UserVO vo){
			return dao.scheck(vo);
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
		
		/** 게시판 내용 조회 */
		public ArrayList<UserVO> gettext(){
			return dao.gettext();
		}
		
		/** 게시판 내용 입력 */
		public boolean pinsert(UserVO vo) {
			boolean result = false;
			
			result = dao.pinsert(vo);
			
			return result;
		}
		
		/** 게시판 - 삭제 검색 **/
		public boolean deletePostSearch(String pno) {
			return dao.deletePostSearch(pno);
		}
		
		/** 게시판 - 삭제 진행 **/
		public boolean deletePost(String pno,String name) {
			return dao.deletePost(pno,name);
		}
		
		/**게시판 이름가져오기 */
		public String rename(String id) {
			String result = "";
			
			result = dao.rename(id);
			
			return result;
				
		}
		/** 게시판 1대1 문의 답변보기 */
		public ArrayList<UserVO> receive(String id){
			return dao.receive(id);
		}
		
		/** 게시판 1대1 문의 보낸리스트  */
		public ArrayList<UserVO> send(String id){
			return dao.send(id);
		}
		
		/** 1:1문의 - 매니저 이름가져오기 */
		public String renameMan(String id) {
			String result = "";
			
			result = dao.renameMan(id);
			
			return result;
				
		}
		
		/** 1:1문의 - 멤버 아이디 가져오기 */
		public String getMid(String Bmname) {
			String result = "";
			
			result = dao.getMid(Bmname);
			
			return result;
				
		}
		/** 1:1문의 - 멤버 아이디 가져오기 */
		public String gettMid(String name) {
			String result = "";
			
			result = dao.gettMid(name);
			
			return result;
				
		}
		
		
		/** 1:1문의 - 매니저 답변 내용 입력 */
		public boolean ainsert(UserVO vo) {
			boolean result = false;
			
			result = dao.ainsert(vo);
			
			return result;
		}
		
		/** 게시판 - 매니저 문의 받기 send테이블 정보 **/ 
		public ArrayList<UserVO> getSendInfo(String id){
			return dao.getSendInfo(id);
		}
		
		/** 게시판 - 매니저 문의 받기 receive테이블 정보 **/
		public ArrayList<UserVO> getReceiveInfo(String id){
			return dao.getReceiveInfo(id);
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
		/** 1:1 문의등록 */
		public boolean send(UserVO vo) {
			return dao.sinsert(vo);
		}
		/** 1:1 매니저아이디가져오기 */
		public String getManagerId(String id) {
			return dao.getManaId(id);
		}
		
}
