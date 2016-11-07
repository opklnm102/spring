package me.dong.comment;

import lombok.RequiredArgsConstructor;
import me.dong.post.Post;
import me.dong.post.PostRepository;
import me.dong.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostRepository postRepository;

    @ModelAttribute
    public Post post(@ModelAttribute CommentDto commentDto){
        return postRepository.findOne(commentDto.getPostId());
    }

    @PostMapping
    public String createComment(@ModelAttribute @Valid CommentDto commentDto, BindingResult bindingResult, Model model, @AuthenticationPrincipal User user){

        if(bindingResult.hasErrors()){
            return "post/post";
        }
        // comment를 생성할 때 연관관계의 post를 넣어줘야 한다.
        //Todo: 개선 service에서 postId가 있는지 검증, 영속객체를 넣는다.
        model.addAttribute("comment", commentService.createComment(
                new Comment(commentDto.getContent(),
                        new Post(commentDto.getPostId()), user)));
        return "redirect:/posts/" + commentDto.getPostId();
    }

    @PostMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/posts/" + postId;
    }
}
