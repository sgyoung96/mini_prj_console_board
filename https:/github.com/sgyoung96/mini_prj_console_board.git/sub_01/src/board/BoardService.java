package board;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardService {

	private BoardDao dao;
	private BoardVo vo;
	private ArrayList<BoardVo> vos;
	private boolean isRunning = true;
	
	private final String alertTitle = "글의 제목을 입력해 주세요.";
	private final String alertContent = "글의 내용을 입력해 주세요.";
	private final String noTitle = "글의 제목이 입력되지 않았습니다.";
	private final String noContent = "글의 내용이 입력되지 않았습니다.";
	private final String overTitle = "글의 제목이 25자를 넘을 수 없습니다.(영문 50자)";
	private final String overContent = "글의 내용이 50자를 넘을 수 없스빈다.(영문 100자)";
	
	byte[] titleByte = new byte[50];
	byte[] contentByte = new byte[100];
	
	private String flag = "";
	
	public BoardService () {
		dao = new BoardDao();
	}
	
	public String writePost (Scanner scanner) {
		vo = new BoardVo();
		while (isRunning) {
			System.out.println(alertTitle);
			vo.setTitle(scanner.nextLine());
			if (vo.getTitle().isEmpty()) {
				System.out.println(noTitle);
			} else {
				System.out.println(alertContent);
				vo.setContent(scanner.nextLine());
				if (vo.getContent().isEmpty()) {
					System.out.println(noContent);
				} else {
					if (vo.getTitle().getBytes().length > titleByte.length) {
						System.out.println(overTitle);
					} else if (vo.getContent().getBytes().length > contentByte.length) {
						System.out.println(overContent);
					} else {
						flag = dao.writePost(vo);
						isRunning = false;
					}
				}
			}
		}
		return flag;
	}
	
	public String showLists (Scanner scanner) {
		return "";
	}
	
	public String selPostDetail (Scanner scanner) {
		return "";
	}
	
	public String modSelPost (Scanner scanner) {
		return "";
	}
	
	public String rmSelPost (Scanner scanner) {
		return "";
	}
	
	public String selByWriter (Scanner scanner) {
		return "";
	}
	
	public String selByTitle (Scanner scanner) {
		return "";
	}

}
