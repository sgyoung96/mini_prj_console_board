package board;

import java.io.Serializable;

public class BoardVo implements Serializable {

	private int num;
	private String writer;
	private String writedDate;
	private String title;
	private String content;
	
	public BoardVo() {
		super();
	}
	
	public BoardVo(int num, String writer, String writedDate, String title, String content) {
		super();
		this.num = num;
		this.writer = writer;
		this.writedDate = writedDate;
		this.title = title;
		this.content = content;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedDate() {
		return writedDate;
	}
	public void setWritedDate(String writedDate) {
		this.writedDate = writedDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", writer=" + writer + ", writedDate=" + writedDate + ", title=" + title
				+ ", content=" + content + "]";
	}
}
