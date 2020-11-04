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
		/** �̿��� */
		public boolean hairCut(UserVO vo) {
			return dao.insert(vo);
		}
		/*
		 * �̿� ���� �ð� �ߺ� üũ
		 */
		public int salonTimeCheck(UserVO vo){
			return dao.scheck(vo);
		}
		/** ���� ���� ��� **/
		public boolean Hospital_reserve(UserVO vo) {
			return dao.Hospital_reserve(vo);
		}
		
		/** ���� ���� �ð� �ߺ� üũ **/ 
		public int reserveTimeCheck(UserVO vo) {
			return dao.hcheck(vo);
		}
		
		
		/** ������ü ����Ʈ ��� */
		public ArrayList<UserVO> hselectList(String id){
			return dao.hselectall(id);
		}
		
		/** �̿���ü ����Ʈ ��� */
		public ArrayList<UserVO> sselectList(String id){
			return dao.sselectall(id);
		}
		
		/** ������������ ã�� */
		public int hsearchhno (String hno) {

			int idx = 0;
			idx = dao.hsearch(hno);
			
			return idx;
		}
		
		/** ������������ ã�� */
		public int ssearchsno (String sno) {
			int idx = 0;
			idx = dao.ssearch(sno);
			
			return idx;
		}
		
		/** ������������ ���� */
		public boolean update(UserVO vo) {
			boolean result = false;
			
			result = dao.hupdate(vo);
			
			return result;
		}
		
		/** ��������  �����ð� üũ */
		public int hcheck(UserVO vo) {
			int result = 0;
			
			result = dao.hcheck(vo);
			
			return result;
		}
		
		/** ��������  �̿�ð� üũ */
		public int scheck(UserVO vo) {
			int result = 0;
			
			result = dao.scheck(vo);
			
			return result;
		}
		
		/** �̿뿹������ ���� */
		public boolean supdate(UserVO vo) {
			boolean result = false;
			
			result = dao.supdate(vo);
			
			return result;
		}
		
		/** ������������ ã��*/
		public UserVO hselectUser(String hno) {
			UserVO vo = null;
			
			vo = dao.hselect(hno);
			return vo;
		}
		
		/** �̿뿹������ ã��*/
		public UserVO sselectUser(String sno) {
			UserVO vo = null;
			
			vo = dao.sselect(sno);
			return vo;
		}
		
		/** �̿뿹�� ���� 
		 * *
		 */
		public boolean sdelete(String sno) {
			boolean result = false;
			
			result = dao.sdelete(sno);
			
			return result;
		}
		
		/** �������� ���� 
		 * *
		 */
		public boolean hdelete(String hno) {
			boolean result = false;
			
			result = dao.hdelete(hno);
			
			return result;
		}
		
		/** ������ ã�� */
		public UserVO selectMember(String mid) {
			UserVO vo = null;
			
			vo = dao.mselect(mid);
			return vo;
		}
		
		/** ������ ���� 
		 */
		public boolean memberupdate(UserVO vo) {
			boolean result = false;
			
			result = dao.memberupdate(vo);
			
			return result;
		}
		
		/** ������ ������ �������� Ȯ�� 
		 * 
		 */
		public int lastcheckh(String mid) {
			int result = 0;
			
			result = dao.lastcheckh(mid);
			
			return result;
		}
		
		
		/** ������ ������ �̿뿹�� Ȯ�� 
		 * 
		 */
		public int lastchecks(String mid) {
			int result = 0;
			
			result = dao.lastchecks(mid);
			
			return result;
		}
		
		/** �Խ��� ���� ��ȸ */
		public ArrayList<UserVO> gettext(){
			return dao.gettext();
		}
		
		/** �Խ��� ���� �Է� */
		public boolean pinsert(UserVO vo) {
			boolean result = false;
			
			result = dao.pinsert(vo);
			
			return result;
		}
		
		/** �Խ��� - ���� �˻� **/
		public boolean deletePostSearch(String pno) {
			return dao.deletePostSearch(pno);
		}
		
		/** �Խ��� - ���� ���� **/
		public boolean deletePost(String pno,String name) {
			return dao.deletePost(pno,name);
		}
		
		/**�Խ��� �̸��������� */
		public String rename(String id) {
			String result = "";
			
			result = dao.rename(id);
			
			return result;
				
		}
		/** �Խ��� 1��1 ���� �亯���� */
		public ArrayList<UserVO> receive(String id){
			return dao.receive(id);
		}
		
		/** �Խ��� 1��1 ���� ��������Ʈ  */
		public ArrayList<UserVO> send(String id){
			return dao.send(id);
		}
		
		/** 1:1���� - �Ŵ��� �̸��������� */
		public String renameMan(String id) {
			String result = "";
			
			result = dao.renameMan(id);
			
			return result;
				
		}
		
		/** 1:1���� - ��� ���̵� �������� */
		public String getMid(String Bmname) {
			String result = "";
			
			result = dao.getMid(Bmname);
			
			return result;
				
		}
		/** 1:1���� - ��� ���̵� �������� */
		public String gettMid(String name) {
			String result = "";
			
			result = dao.gettMid(name);
			
			return result;
				
		}
		
		
		/** 1:1���� - �Ŵ��� �亯 ���� �Է� */
		public boolean ainsert(UserVO vo) {
			boolean result = false;
			
			result = dao.ainsert(vo);
			
			return result;
		}
		
		/** �Խ��� - �Ŵ��� ���� �ޱ� send���̺� ���� **/ 
		public ArrayList<UserVO> getSendInfo(String id){
			return dao.getSendInfo(id);
		}
		
		/** �Խ��� - �Ŵ��� ���� �ޱ� receive���̺� ���� **/
		public ArrayList<UserVO> getReceiveInfo(String id){
			return dao.getReceiveInfo(id);
		}
		
		/** �α��� */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
		}
		
		/** ȸ�� �α��� */
		public boolean memlogin(String id ,String pass) {
			return dao.memlogin(id,pass);
		}
		
		/** �Ŵ��� �α��� */
		public boolean manlogin(String id ,String pass) {
			return dao.manlogin(id,pass);
		}
		
		/** ȸ������ **/
		public boolean memRegister(UserVO vo) {
			return dao.register(vo);
		}
		
		/** �Ŵ��� - ��ü ��� ����Ʈ ��� */
		public ArrayList<UserVO> memberSelectList(){
			return dao.memberSelect();
		}
		
		/** �Ŵ��� - ���� �˻� **/
		public int searchMid(String mid) {
			return dao.searchMid(mid);
		}
		
		/** �Ŵ��� - ���� ��� ���� ��� **/
		public UserVO selectMemInfo(String mid) {
			return dao.selectMemInfo(mid);
		}
		
		/** �Ŵ��� - ��� ���� �Ϸ� **/
		public boolean menUpdate(UserVO vo) {
			return dao.menUpdate(vo);
		}
		
		/** �Ŵ��� - ���� �˻�**/
		public boolean deleteSearch(String mid) {
			return dao.delSelect(mid);
		}
		
		/** �Ŵ��� - ���� **/
		public boolean delete(String mid) {
			return dao.delete(mid);
		}
		/** 1:1 ���ǵ�� */
		public boolean send(UserVO vo) {
			return dao.sinsert(vo);
		}
		/** 1:1 �Ŵ������̵������� */
		public String getManagerId(String id) {
			return dao.getManaId(id);
		}
		
}
