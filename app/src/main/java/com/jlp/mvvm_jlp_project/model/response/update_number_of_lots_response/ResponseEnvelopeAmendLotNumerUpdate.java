package com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response;

import com.jlp.mvvm_jlp_project.model.response.Header;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "soapenc", reference = "http://schemas.xmlsoap.org/soap/encoding/"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
})
public class ResponseEnvelopeAmendLotNumerUpdate {

    @Element(name = "Body", required = false)
    private ResponseBodyAmendLotNumerUpdate responseBodyAmendLotNumerUpdate;

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyAmendLotNumerUpdate getResponseBodyAmendLotNumerUpdate() {
        return responseBodyAmendLotNumerUpdate;
    }

    public void setResponseBodyAmendLotNumerUpdate(ResponseBodyAmendLotNumerUpdate responseBodyAmendLotNumerUpdate) {
        this.responseBodyAmendLotNumerUpdate = responseBodyAmendLotNumerUpdate;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


}
