package me.dong.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
http://blog.eomdev.com/java/2016/01/04/jpa_with_java8.html
 Java8이 릴리즈 되기전에 JPA 2.1이 나왔기 때문에 JPA 2.1이 Java8의 날짜와 시간 API를 지원X
 -> AttributeConverter 인터페이스를 구현하여 LocalDateTime과 Timestamp를 서로 변환시키는 Converter를 만들어 등록
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return (localDateTime == null ? null : Timestamp.valueOf(localDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}
