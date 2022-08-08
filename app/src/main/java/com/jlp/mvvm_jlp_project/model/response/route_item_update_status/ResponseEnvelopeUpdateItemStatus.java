package com.jlp.mvvm_jlp_project.model.response.route_item_update_status;

import com.jlp.mvvm_jlp_project.model.response.Header;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;


@Root(name = "Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "soapenc", reference = "http://schemas.xmlsoap.org/soap/encoding/"),
        @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
})
public class ResponseEnvelopeUpdateItemStatus {

    @Element(name = "Body", required = false)
    private ResponseBodyUpdateItemStatus responseBody;

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyUpdateItemStatus getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBodyUpdateItemStatus responseBody) {
        this.responseBody = responseBody;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
