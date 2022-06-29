package com.jlp.mvvm_jlp_project.model.response;

import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseBodyAuthenticateUser;

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
public class EnvelopeResponse {

    @Element(name = "Body", required = false)
    private ResponseBodyAuthenticateUser body = new ResponseBodyAuthenticateUser();

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyAuthenticateUser getBody() {
        return body;
    }

    public void setBody(ResponseBodyAuthenticateUser body) {
        this.body = body;
    }
}
