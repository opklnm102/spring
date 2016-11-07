package me.dong.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dong.post.Post;
import me.dong.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Comment와 Post의 관계 - N:1
 */
@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"post"})
@EqualsAndHashCode(exclude = {"post"})
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Comment(String content, Post post, User user){
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public Comment(String content, Post post){
        this.content = content;
        this.post = post;
    }
}
