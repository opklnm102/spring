package me.dong.post;

import lombok.RequiredArgsConstructor;
import me.dong.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(Post post){
        post.setRegDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findByIdAndStatus(Long id, PostStatus status){
        Post post = postRepository.findByIdAndStatus(id, status);
        if(post == null){
            throw new NotFoundException(id + " not found");
        }
        return post;
    }

    /*
    update 한뤼에 조회를 날리는 메소드가 없다 -> JPA 영속성에서 처리한다.
    Transaction 범위에서는 영속상태의 객체를 만들 수 있다.
    영속상태인 객체가 변경되고 Transaction이 끝나면 변경된 객체의 속성을 감지하여 update 쿼리를 날린다.
    -> 변경감지
    그래서 update메소드는 존재하지 않고, 영속상태의 객체에 변경만 해준다.
     */
    public Post updatePost(Long id, Post post){
        Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
        if(oldPost == null){
            throw new NotFoundException(id + " not found");
        }

        oldPost.setContent(post.getContent());
        oldPost.setContent(post.getCode());
        oldPost.setTitle(post.getTitle());
        return oldPost;
    }

    public void deletePost(Long id){
        Post oldPost = postRepository.findByIdAndStatus(id, PostStatus.Y);
        if(oldPost == null){
            throw new NotFoundException(id + " not found");
        }
        oldPost.setStatus(PostStatus.N);
    }
}
