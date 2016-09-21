package me.dong.controller.web;

import me.dong.model.domain.Post;
import me.dong.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String form(Post post){
        return "form";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Post post){
        post.setRegDate(new Date());
        return "redirect:/post/" + postRepository.save(post).getId();
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList", postList);
        return "blog";
    }

    @RequestMapping("/{id}")
    public String view(Model model, @PathVariable Long id){
        Post post = postRepository.findOne(id);
        model.addAttribute("post", post);
        return "post";
    }

}
