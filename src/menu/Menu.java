package menu;

import java.util.Scanner;

import board.BoardService;
import main.Constants;
import member.MemberService;

/**
 * 회원관리 : 회원가입, 로그인, 로그아웃, 내정보확인, 내정보수정, 탈퇴
 * 게시판 : 글작성, 글전체목록, 글상세, 내글수정, 내글삭제, 작성자로 검색, 제목으로 검색
 * 종료
 * @author gayeong
 *
 */
public class Menu {

	private String defaultAlert = "메뉴 번호를 입력해 주세요.";
	private String goBack = "이전 메뉴로 돌아갑니다.";
	private String error = "잘못 입력하셨습니다. 다시 입력해 주세요.";
	
	private String menuDepth1 = "1. 회원관리 2. 게시판 3. 종료";
	private String menuMember = "1. 회원가입 2. 로그인 3. 로그아웃 4. 내정보확인 5. 내정보수정 6. 탈퇴 7. 이전메뉴로";
	private String menuBoard = "1. 글작성 2. 글전체목록 3. 글상세 4. 내글수정 5. 내글삭제 6. 작성자로검색 7. 제목으로검색 8. 이전메뉴로";
	private int iptMenuNo = 0;
	private String selMenuNo = "";
	
	private Scanner scanner;
	public boolean isRunning = true;
	private String flag = "";
	
	private MemberService mService;
	private BoardService bService;
	
	public Menu() {
		mService = new MemberService();
		bService = new BoardService();
	}
	
	public Menu(Scanner scanner) {
		this.scanner = scanner;
		System.out.println(defaultAlert);
	}
	
	public String menuDepth1(int selectedMenu) {
		while (isRunning) {
			System.out.println(menuDepth1);
			iptMenuNo = Integer.parseInt(scanner.nextLine());
			
			if (iptMenuNo == 1 || selectedMenu == 1) {
				selMenuNo = menuMember("");
				isRunning = false;
			} else if (iptMenuNo == 2 || selectedMenu == 2) {
				if (Constants.mLoginId == null) {
					System.out.println("로그인 후 이용 가능한 메뉴입니다.");
					selMenuNo = "20";
					isRunning = true;
				} else {
					selMenuNo = menuBoard("");
					isRunning = false;
				}
			} else if (iptMenuNo == 3 || selectedMenu == 3) {
				exitProgram();
				isRunning = false;
			} else {
				System.out.println(error);
				selMenuNo = "10";
				isRunning = true;
			}
		}
		return selMenuNo;
	}
	
	public String menuMember(String selectedMenu) {
		mService = new MemberService();
		isRunning = true;
		System.out.println(defaultAlert + "\n" + menuMember);
		iptMenuNo = Integer.parseInt(scanner.nextLine());
		
		while (isRunning) {
			if (iptMenuNo == 1) {
				flag = mService.register(scanner);
				isRunning = false;
			} else if (iptMenuNo == 2) {
				flag = mService.login(scanner);
				isRunning = false;
			} else if (iptMenuNo == 3) {
				flag = mService.logout();
				System.out.println("로그아웃되었습니다." + "\n" + goBack);
				isRunning = false;
			} else if (iptMenuNo == 4) {
				flag = mService.chkMyInfo();
				isRunning = false;
			} else if (iptMenuNo == 5) {
				flag = mService.setMyInfo(scanner);
				isRunning = false;
			} else if (iptMenuNo == 6) {
				flag = mService.delUserInfo();
				isRunning = false;
			} else if (iptMenuNo == 7) {
				System.out.println(goBack);
				menuDepth1(1);
				isRunning = false;
			} else if (selectedMenu.equals("4")) {
				flag = mService.chkMyInfo();
				isRunning = false;
			} else if (selectedMenu.equals("5")) {
				flag = mService.setMyInfo(scanner);
				isRunning = false;
			} else if (selectedMenu.equals("7")) {
				System.out.println(goBack);
				menuDepth1(1);
				isRunning = false;
			} else {
				flag = "00";
				System.out.println(error);
				isRunning = false;
			}
		}
		return flag;
	}
	
	public String menuBoard(String selectedMenu) {
		bService = new BoardService();
		isRunning = true;
		System.out.println(defaultAlert + "\n" + menuBoard);
		iptMenuNo = Integer.parseInt(scanner.nextLine());
		
		while (isRunning) {
			if (iptMenuNo == 1) {
				flag = bService.writePost(scanner);
				isRunning = false;
			} else if (iptMenuNo == 2) {
				flag = bService.showLists(scanner);
				isRunning = false;
			} else if (iptMenuNo == 3) {
				flag = bService.selPostDetail(scanner);
				isRunning = false;
			} else if (iptMenuNo == 4) {
				flag = bService.modSelPost(scanner);
				isRunning = false;
			} else if (iptMenuNo == 5) {
				flag = bService.rmSelPost(scanner);
				isRunning = false;
			} else if (iptMenuNo == 6) {
				flag = bService.selByWriter(scanner);
				isRunning = false;
			} else if (iptMenuNo == 7) {
				flag = bService.selByTitle(scanner);
				isRunning = false;
			} else if (iptMenuNo == 8) {
				System.out.println(goBack);
				menuDepth1(1);
				isRunning = false;
			} else {
				flag = "00";
				System.out.println(error);
				isRunning = false;
			}
		}
		return flag;
	}
	
	private void exitProgram() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(0);
	}
}
