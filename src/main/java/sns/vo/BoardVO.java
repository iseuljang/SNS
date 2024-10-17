package sns.vo;

public class BoardVO {
	private String fno;
	private String uno;
	private String title;
	private String content;
	private String rdate;
	private String hit;
	private String state;
	private String filename;
	private String uid;
	
	public BoardVO() {}

	public BoardVO(String fno, String uno, String title, String content, String rdate, String hit, String state,
			String filename, String uid) {
		this.fno = fno;
		this.uno = uno;
		this.title = title;
		this.content = content;
		this.rdate = rdate;
		this.hit = hit;
		this.state = state;
		this.filename = filename;
		this.uid = uid;
	}

	public String getFno()      {	return fno;		 }
	public String getUno()      {	return uno;		 }
	public String getTitle()    {	return title;	 }
	public String getContent()  {	return content;	 }
	public String getRdate()    {	return rdate;	 }
	public String getHit()      {	return hit;		 }
	public String getState()    {	return state;	 }
	public String getFilename() {	return filename; }
	public String getUid()      {	return uid;	     }

	public void setFno(String fno)           {	this.fno = fno;			  }
	public void setUno(String uno)           {	this.uno = uno;			  }
	public void setTitle(String title)       {	this.title = title;	      }
	public void setContent(String content)   {	this.content = content;	  }
	public void setRdate(String rdate)       {	this.rdate = rdate;	      }
	public void setHit(String hit)           {	this.hit = hit;	          }
	public void setState(String state)       {	this.state = state;	      }
	public void setFilename(String filename) {	this.filename = filename; }
	public void setUid(String uid)           {	this.uid = uid;	          }
		
}