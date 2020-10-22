package project_hospital;

import java.util.ArrayList;

public class HospitalMgmSystem {
	//field
		HospitalDAO dao = new HospitalDAO();
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		//Method
		
		/** ��ü ����Ʈ ��� */
		public ArrayList<UserVO> selectList(){
			return dao.select();
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
		
		/** �α��� */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
		}
}
