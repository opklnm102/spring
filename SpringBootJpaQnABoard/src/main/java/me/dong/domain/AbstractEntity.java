package me.dong.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Dong on 2017-02-06.
 */
@MappedSuperclass  // JPA에서 부모클래스로 사용
@EntityListeners(AuditingEntityListener.class)  // 데이터의 변화를 파악해 Auditing 해준다
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @CreatedDate  // 자동으로 시간변경을 해줌
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getFormattedCreatedAt() {
        return getFormattedDate(createdAt, "yyyy.MM.dd HH:mm:ss");
    }

    public String getFormattedUpdatedAt() {
        return getFormattedDate(updatedAt, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
