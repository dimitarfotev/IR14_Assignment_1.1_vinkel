package com.project.lucene.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TEXT")
@XmlAccessorType(XmlAccessType.FIELD)
public class TEXT
{
    private String BODY;

    private String DATELINE;

    private String TITLE;
    
    public String getBODY ()
    {
        return BODY;
    }

    public void setBODY (String BODY)
    {
        this.BODY = BODY;
    }

    public String getDATELINE ()
    {
        return DATELINE;
    }

    public void setDATELINE (String DATELINE)
    {
        this.DATELINE = DATELINE;
    }

    public String getTITLE ()
    {
        return TITLE;
    }

    public void setTITLE (String TITLE)
    {
        this.TITLE = TITLE;
    }
}
