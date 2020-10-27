package project_hospital;

import java.util.ArrayList;


public class HospitalMgmSystem {
	//field
		HospitalDAO dao = new HospitalDAO();
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		//Method
		/** �̿��� */
		public boolean hairCut(UserVO vo) {
			return dao.insert(vo);
		}
		
		/** ���� ���� ��� **/
		public boolean Hospital_reserve(UserVO vo) {
			return dao.Hospital_reserve(vo);
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
		
		
		/** �α��� */
		public boolean login(String id ,String pass) {
			boolean result = false;
			
			return dao.login(id,pass);
		}
		
}
