package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement(localName = "USERINFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    @JacksonXmlProperty(isAttribute = true)
    private Object LDAPID;
    @JacksonXmlProperty(isAttribute = true)
    private Object CORPORATION;
    @JacksonXmlProperty(isAttribute = true)
    private Object SUBCOMPANY;
    @JacksonXmlProperty(isAttribute = true)
    private Object BUREAU;
    @JacksonXmlProperty(isAttribute = true)
    private Object DEPARTMENT;
    @JacksonXmlProperty(isAttribute = true)
    private Object NAME;
    @JacksonXmlProperty(isAttribute = true)
    private Object ISLDAPID;
}
