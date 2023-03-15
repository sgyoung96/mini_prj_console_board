package member;

import java.util.Scanner;

import main.Constants;
import menu.Menu;

public class MemberService {
	
	private MemberDao dao;
	private MemberVo vo;
	private boolean isRunning = true;
	
	public MemberService() {
		dao = new MemberDao();
	}

	public String register(Scanner scanner) {
		String flag = "";
		System.out.println("회원가입을 시작해 주세요.");
		while (isRunning) {
			System.out.println("ID 를 입력하세요.");
			String id = scanner.nextLine();
			vo = new MemberVo();
			if (dao.chkExistsId(vo)) { // 아이디 중복 체크
				vo.setId(id);
				System.out.println("PW 를 입력하세요.");
				vo.setPwd(scanner.nextLine());
				System.out.println("이름을 입력해주세요.");
				vo.setName(scanner.nextLine());
				System.out.println("이메일을 입력해주세요.");
				vo.setEmail(scanner.nextLine());
				flag = dao.register(vo);
				break;
			} else {
				vo.setId(null);
				System.out.println("중복된 ID 입니다.");
			}
			isRunning = false;
		}
		return flag;
	}

	public String login(Scanner scanner) {
		vo = new MemberVo();
		System.out.println("ID 를 입력하세요.");
		vo.setId(scanner.nextLine());
		System.out.println("PW 를 입력하세요.");
		vo.setPwd(scanner.nextLine());
		return dao.login(vo);
	}

	public String logout() {
		Constants.mLoginId = null;
		vo = null;
		return "13";
	}

	public String chkMyInfo() {
		vo = new MemberVo();
		vo.setId(Constants.mLoginId);
		return dao.chkMyInfo(vo);
	}

	public String setMyInfo(Scanner scanner) {
		vo = new MemberVo();
		vo.setId(Constants.mLoginId);
		System.out.println("수정할 비밀번호를 입력하세요.");
		vo.setPwd(scanner.nextLine());
		System.out.println("수정할 이름을 입력하세요.");
		vo.setName(scanner.nextLine());
		return dao.setMyInfo(vo);
	}

	public String delUserInfo() {
		return dao.delUserInfo();
	}
}


