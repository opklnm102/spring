package me.dong.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by Dong on 2017-02-03.
 */
@Entity
public class Answer extends AbstractEntity {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @JsonProperty
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @JsonProperty
    private Question question;

    @Lob
    @JsonProperty
    private String contents;

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
//        this.createdAt = LocalDateTime.now(TimeZone.getTimeZone("Asia/Seoul").toZoneId());
    }

    @Override
    public String toString() {
        return "Answer{" +
                super.toString() +
                "writer=" + writer +
                ", question=" + question +
                ", contents='" + contents + '\'' +
                '}';
    }

    public boolean matchWriter(User loginUser) {
        return loginUser.equals(this.writer);
    }
}
