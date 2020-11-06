package project_hospital;

public class UserVO {
	String sid, spass; 										// Manager
	String mno,mid,mpass,mphone,mname,mkind;				// Member
	String hno,hsymptom,hyear,hmonth,hday,htime,hmid;		// Hbooking
	String sno,syear,smonth,sday,stime,sgender,stext,smid;  // SalonRes
	String rno,pno,ptitle,ptext,pdate,pname;				// board
	String aid,atitle,atext,adate,amname,asname,amid,asid;  // 멤버가 받을 내용들
	String bid,btitle,btext,bdate,bmname,bsname,bmid,bsid;  // 멤버가 보낸내용

	public void setAmid(String amid) {
		this.amid = amid;
	}
	public void setAsid(String asid) {
		this.asid = asid;
	}
	public String getPname() {
		return pname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAtitle() {
		return atitle;
	}
	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}
	public String getAtext() {
		return atext;
	}
	public void setAtext(String atext) {
		this.atext = atext;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public String getAmname() {
		return amname;
	}
	public void setAmname(String amname) {
		this.amname = amname;
	}
	public String getAsname() {
		return asname;
	}
	public void setAsname(String asname) {
		this.asname = asname;
	}
	public String getAmid() {
		return amid;
	}
	public void setImid(String amid) {
		this.amid = amid;
	}
	public String getAsid() {
		return asid;
	}
	public void setIsid(String asid) {
		this.asid = asid;
	}
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		this.bmid = bmid;
	}
	public String getBsid() {
		return bsid;
	}
	public void setBsid(String bsid) {
		this.bsid = bsid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBtext() {
		return btext;
	}
	public void setBtext(String btext) {
		this.btext = btext;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBmname() {
		return bmname;
	}
	public void setBmname(String bmname) {
		this.bmname = bmname;
	}
	public String getBsname() {
		return bsname;
	}
	public void setBsname(String bsname) {
		this.bsname = bsname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		this.spass = spass;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpass() {
		return mpass;
	}
	public void setMpass(String mpass) {
		this.mpass = mpass;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMkind() {
		return mkind;
	}
	public void setMkind(String mkind) {
		this.mkind = mkind;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getHsymptom() {
		return hsymptom;
	}
	public void setHsymptom(String hsymptom) {
		this.hsymptom = hsymptom;
	}
	public String getHyear() {
		return hyear;
	}
	public void setHyear(String hyear) {
		this.hyear = hyear;
	}
	public String getHmonth() {
		return hmonth;
	}
	public void setHmonth(String hmonth) {
		this.hmonth = hmonth;
	}
	public String getHday() {
		return hday;
	}
	public void setHday(String hday) {
		this.hday = hday;
	}
	public String getHtime() {
		return htime;
	}
	public void setHtime(String htime) {
		this.htime = htime;
	}
	public String getHmid() {
		return hmid;
	}
	public void setHmid(String hmid) {
		this.hmid = hmid;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSyear() {
		return syear;
	}
	public void setSyear(String syear) {
		this.syear = syear;
	}
	public String getSmonth() {
		return smonth;
	}
	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}
	public String getSday() {
		return sday;
	}
	public void setSday(String sday) {
		this.sday = sday;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		this.sgender = sgender;
	}
	public String getStext() {
		return stext;
	}
	public void setStext(String stext) {
		this.stext = stext;
	}
	public String getSmid() {
		return smid;
	}
	public void setSmid(String smid) {
		this.smid = smid;
	}
	
}
