package com.project.lucene.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "LEWIS")
@XmlAccessorType(XmlAccessType.FIELD)
public class LEWIS
{   @XmlElement(name = "REUTERS")
    private List<REUTERS> REUTERS;

    public List<REUTERS> getREUTERS ()
    {
        return REUTERS;
    }

    public void setREUTERS (List<REUTERS> REUTERS)
    {
        this.REUTERS = REUTERS;
    }
}