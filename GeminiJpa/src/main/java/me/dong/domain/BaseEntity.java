package me.dong.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  //Parent Entity Class 지원
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;  // id의 수동적인 제어를 막기 위해 setter를 생성하지 않는다.

    @Column(updatable = false)  //update 못하게 설정
    protected LocalDateTime createdAt;

    @Column
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onPersist(){
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * test등의 용도로 setter 생성
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
}
