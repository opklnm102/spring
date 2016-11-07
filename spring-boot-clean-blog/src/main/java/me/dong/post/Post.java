package me.dong.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dong.category.Category;
import me.dong.comment.Comment;
import me.dong.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"category", "comments"})
@EqualsAndHashCode(exclude = {"category", "comments"})
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @Lob  // text type으로 사용하기 위해
    @NotNull
    private String content;

    @Lob
    private String code;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")  // 외래키 매핑시 이용
    private Category category;

    // mappedBy - 연관관계 주인 설정. 주인O(읽기, 쓰기), 주인X(읽기)
    // mappedBy가 있으면 주인X.
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Post(Long id){
        this.id = id;
    }

    public Post(String title, PostStatus status){
        this.title = title;
        this.status = status;
    }

    public Post(String title, String content, String code, PostStatus status) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
    }

    public Post(long id, String title, String content, String code, PostStatus status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
    }

    public Post(String title, String content, String code, PostStatus status, Category category) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
        this.category = category;
    }

    public Post(String title, String content, String code, PostStatus status, Category category, User user) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.status = status;
        this.category = category;
        this.user = user;
    }
}

