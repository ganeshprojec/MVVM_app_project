package com.jlp.mvvm_jlp_project.model.response.find_handover_details;

import com.jlp.mvvm_jlp_project.model.response.Header;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "soapenc", reference = "http://schemas.xmlsoap.org/soap/encoding/"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
})
public class ResponseEnvelopeFindHandoverDetails {

    @Element(name = "Body", required = false)
    private ResponseBodyFindHandoverDetails responseBodyFindHandoverDetails;

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyFindHandoverDetails getResponseBodyFindHandoverDetails() {
        return responseBodyFindHandoverDetails;
    }

    public void setResponseBodyFindHandoverDetails(ResponseBodyFindHandoverDetails responseBodyFindHandoverDetails) {
        this.responseBodyFindHandoverDetails = responseBodyFindHandoverDetails;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}