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
		
		/** ��ü ����Ʈ ��� */
		public ArrayList<UserVO> selectList(){
			return dao.select();
		}
		
		/** ��ü ��� ����Ʈ ��� */
		public ArrayList<UserVO> memberSelectList(){
			return dao.memberSelect();
		}
		
		/** �������� ã�� */
		public int searchRno (String rno) {
			int idx = 0;
			idx = dao.search(rno);
			
			return idx;
		}
		
		/** �������� ���� */
		public boolean update(UserVO vo) {
			boolean result = false;
			
			result = dao.update(vo);
			
			return result;
		}
		
		/** �������� ���� */
		public UserVO selectUser(String rno) {
			UserVO vo = null;
			
			vo = dao.select(rno);
			return vo;
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
		
		/** �˻� - ����**/
		public boolean deleteSearch(String mid) {
			return dao.delSelect(mid);
		}
		
		/** ���� **/
		public boolean delete(String mid) {
			return dao.delete(mid);
		}
}
