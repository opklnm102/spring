package me.dong.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *  응답에 필요한 값을 저장하기 위한 VO 클래스
 *  객체가 생성될 때 현재시간에 대한 Timestamp와 DateString을 가지고 있다.
 */
@XmlRootElement
public class DateVO {

    private Long timestamp;

    private String dateString;

    public DateVO(){
        Date now = new Date();
        this.timestamp = now.getTime();
        this.dateString = now.toString();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
