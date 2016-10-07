package me.dong.model.domain;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 도메인 클래스 추상화
 */
@MappedSuperclass  //상속관계 매핑에서 부모 클래스로 지정
//어떤 필드를 Json으로 변환할지 옵션 설정
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility =
        JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(value = JsonInclude.Include.ALWAYS)  //필드값 존재 여부에 따라 Json에 포함할지 여부
@EntityListeners(AuditingEntityListener.class)  //EntityListeners가 있고 @CreateDate, @LastModifiedDate 등이 있으면 자동으로 저장
public abstract class AbstractEntity<K extends Serializable> implements Serializable {

    /**
     * 생성일자
     */
    @Column(name = "created_at", insertable = true, updatable = false)
    @CreatedDate
    protected Date createdAt;

    /**
     * 생성자
     */
    @Column(name = "created_by", insertable = true, updatable = false)
    @CreatedBy  //자동으로 지정
    protected Long createdBy;

    /**
     * 수정일자
     */
    @Column(name = "updated_at", insertable = true, updatable = true)
    @LastModifiedDate
    protected Date updatedAt;

    /**
     * 수정자
     */
    @Column(name = "updated_by", insertable = true, updatable = true)
    @LastModifiedBy
    protected Long updatedBy;

    public abstract String toString();

    public abstract K getId();
}
