package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement(localName = "api")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    @JacksonXmlProperty(isAttribute = true)
    private Object name;
    @JacksonXmlProperty(isAttribute = true)
    private Object value;
}
