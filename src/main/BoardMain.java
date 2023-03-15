package main;

import java.util.Scanner;

import menu.Menu;

public class BoardMain {

	public static void main(String[] args) {
		Constants.getInstance();
		String selMenuNo = "";
		boolean isRunning = true;
		String menuFlag = "";
		
		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(scanner);
		selMenuNo = menu.menuDepth1(0);
		menuFlag = selMenuNo.substring(0, 1);
		menu.isRunning = true;
		while(isRunning) {
			switch (menuFlag) {
				case "1": {
					selMenuNo = menu.menuDepth1(1);
					if (selMenuNo.equals("14") || selMenuNo.equals("15")) {
						menuFlag = selMenuNo.substring(0, 1);
						selMenuNo = menu.menuMember(selMenuNo.substring(1));
					} else if (selMenuNo.equals("10")) {
						menuFlag = selMenuNo.substring(0, 1);
						selMenuNo = menu.menuDepth1(1);
					}
					isRunning = true;
					break;
				}
				case "2": {
					if (selMenuNo.equals("20")) {
						selMenuNo = menu.menuDepth1(2);
					} else {
						selMenuNo = selMenuNo.substring(1);
					}
					
					isRunning = true;
					break;
				}
				case "3": {
					menu.menuDepth1(3);
					isRunning = false;
					break;
				}
				default: {
					menu.menuDepth1(0);
					isRunning = true;
				}
			}
			continue;
		}
	}
}