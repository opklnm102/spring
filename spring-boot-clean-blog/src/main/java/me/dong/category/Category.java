package me.dong.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dong.post.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Category와 Post의 관계 - 1:N
 */
@Entity
@Data  //모든 필드에 대한 getter, setter와 toString, equals, hashCode, final로 지정됐거나 @NonNull로 명시된 필드에 대한 값을 받는 생성자 메소드 코드를 생성
@NoArgsConstructor  //빈 생성자 코드 생성
@ToString(exclude = {"posts"})  //객체의 내용을 문자열로 변환해주는 toString() 메소드를 생성. post 필드 제외
@EqualsAndHashCode(exclude = {"posts"})  //코드에서 객체의 비교 등의 용도로 사용되는 equals(), hashCode() 메소드의 코드를 생성. post 필드 제외
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime regDate;

    // LAZY -> Category를 가져올 때 post는 프록시 객체로 가져온다.
    // mappedBy가 있는곳이 주체가 아님. 주체O(R/W), 주체X(R)
    // post를 사용할 때 쿼리한다.
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, LocalDateTime regDate) {
        this.id = id;
        this.name = name;
        this.regDate = regDate;
    }
}
