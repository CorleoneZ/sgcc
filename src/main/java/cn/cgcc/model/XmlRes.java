package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "return")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name="return")
public class XmlRes {
    @JacksonXmlElementWrapper(useWrapping = false)
    private String status;
    @JacksonXmlElementWrapper(useWrapping = false)
    private String message;
    @JacksonXmlElementWrapper(useWrapping = false)
    private String reason;
    @JacksonXmlElementWrapper(useWrapping = false)
    private String Corporation;
    @JacksonXmlProperty(isAttribute = true)
    private List<Value> api;
}
