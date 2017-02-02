package me.dong.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Dong on 2017-02-02.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    private String writer;

    private String title;

    private String contents;

    public Question() {  // JPA에선 default 생성자 필요
    }

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
