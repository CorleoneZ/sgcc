package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "api")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    @JacksonXmlProperty(isAttribute = true)
    private String name;
}
