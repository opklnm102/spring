package me.dong.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dong.comment.Comment;
import me.dong.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = {"comments", "posts"})
@EqualsAndHashCode(exclude = {"comments", "posts"})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private String github;

    private String avatarUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Column
    @Lob
    private String bio;

    public User(String email, String name, String github, String avatarUrl){
        this.email = email;
        this.name = name;
        this.github = github;
        this.avatarUrl = avatarUrl;
    }
}
