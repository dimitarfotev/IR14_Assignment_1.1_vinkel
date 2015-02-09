package com.project.lucene.domain;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "REUTERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class REUTERS

{

	@XmlAttribute(name = "NEWID")
	private String NEWID;

	@XmlElement(name = "TEXT")
	private TEXT TEXT;

	private String DATE;

	public String getNEWID() {
		return NEWID;
	}

	public void setNEWID(String NEWID) {
		this.NEWID = NEWID;
	}

	public TEXT getTEXT() {
		return TEXT;
	}

	public void setTEXT(TEXT TEXT) {
		this.TEXT = TEXT;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String DATE) {
		this.DATE = DATE;
	}

}
