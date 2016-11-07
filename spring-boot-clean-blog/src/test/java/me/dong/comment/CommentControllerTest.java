package me.dong.comment;

import me.dong.post.Post;
import me.dong.post.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
@WithMockUser(username = "opklnm102")
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private PostRepository postRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        given(postRepository.findOne(anyLong())).willReturn(new Post(1L));
    }

    @Test
    public void createComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("test");
        comment.setPost(new Post(1L));
        given(commentService.createComment(comment)).willReturn(comment);

        this.mvc.perform(post("/comments").with(csrf())
                .param("postId", "1")
                .param("content", "test"))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
    }

    @Test
    public void deleteComment() throws Exception {
        this.mvc.perform(post("/comments/1/1").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
    }
}
