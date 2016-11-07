package me.dong.post;

import lombok.RequiredArgsConstructor;
import me.dong.category.Category;
import me.dong.category.CategoryService;
import me.dong.comment.CommentDto;
import me.dong.config.Navigation;
import me.dong.config.Section;
import me.dong.exception.NotFoundException;
import me.dong.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
@Navigation(Section.POST)
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    /*
    포스트 등록, 수정에 카테고리 목록이 필요
    */
    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")  //spring 4.3에 추가
    public String findByPost(@PathVariable Long id, Model model, @ModelAttribute CommentDto commentDto) {
        Post post = postService.findByIdAndStatus(id, PostStatus.Y);
        if (post == null) {
            throw new NotFoundException(id + " not found");
        }
        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping(value = "/new")
    public String newPost(PostDto postDto) {
        return "post/new";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postService.findByIdAndStatus(id, PostStatus.Y);
        if (post == null) {
            throw new NotFoundException(id + " not found");
        }
        PostDto createPost = new PostDto();
        createPost.setCategoryId(post.getCategory().getId());
        createPost.setCategoryName(post.getCategory().getName());
        createPost.setTitle(post.getTitle());
        createPost.setCode(post.getCode());
        createPost.setContent(post.getContent());
        createPost.setId(id);
        model.addAttribute("editPost", createPost);
        return "post/edit";
    }

    @PostMapping
    public String createPost(@ModelAttribute @Valid PostDto createPost, BindingResult bindingResult, Model model,
                             @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        Post post = new Post(createPost.getTitle(),
                createPost.getContent(),
                createPost.getCode(),
                PostStatus.Y,
                new Category(createPost.getCategoryId()), user);

        Post newPost = postService.createPost(post);
        model.addAttribute("post", newPost);
        return "redirect:/posts/" + newPost.getId();
    }

    @PostMapping(value = "/{id}/edit")
    public String modifyPost(@PathVariable Long id,
                             @ModelAttribute(value = "editPost") @Valid PostDto createPost, BindingResult bindingResult,
                             @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        postService.updatePost(id,
                new Post(createPost.getTitle(),
                        createPost.getContent(),
                        createPost.getCode(),
                        PostStatus.Y,
                        new Category(createPost.getCategoryId()),
                        user));
        return "redirect:/posts/" + id;
    }

    @PostMapping(value = "{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/#/";
    }
}
