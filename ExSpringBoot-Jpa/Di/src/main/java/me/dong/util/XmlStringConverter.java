package me.dong.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * 객체를 XML String으로 변환하는 유틸 클래스
 */
public class XmlStringConverter implements StringConverter {

    @Override
    public String toString(Object obj) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.marshal(obj, writer);
        } catch (Exception e) {
            throw new RuntimeException(String.format("object convert to xml string error..., %s", e.toString()), e);
        }
        return writer.toString();
    }
}
