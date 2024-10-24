package sns.vo;

public class UserVO {
	private String uno;
	private String uid;
	private String upw;
	private String unick;
	private String uemail;
	private String urdate;
	private String uauthor;
	private String ustate;
	private String pname;
	private String fname;
	
	public String getUno()     {	return uno;	    }
	public String getUid()     {	return uid;	    }
	public String getUpw()     {	return upw;	    }
	public String getUnick()   {	return unick;	}
	public String getUemail()  {	return uemail;	}
	public String getUrdate()  {	return urdate;	}
	public String getUstate()  {	return ustate;	}
	public String getUauthor() {	return uauthor;	}
	public String getPname()   {	return pname;	}
	public String getFname()   {	return fname;	}
	
	public void setUno(String uno)         {	this.uno = uno;	        }
	public void setUid(String uid)         {	this.uid = uid;	        }
	public void setUpw(String upw)         {	this.upw = upw;	        }
	public void setUnick(String unick)     {	this.unick = unick;  	}
	public void setUemail(String uemail)   {	this.uemail = uemail;	}
	public void setUrdate(String urdate)   {	this.urdate = urdate;	}
	public void setUstate(String ustate)   {	this.ustate = ustate;	}
	public void setUauthor(String uauthor) {	this.uauthor = uauthor;	}
	public void setPname(String pname)     {	this.pname = pname;	    }
	public void setFname(String fname)     {	this.fname = fname;	    }
}