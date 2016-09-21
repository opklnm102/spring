package me.dong.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.dong.rest.dto.Comment;
import me.dong.rest.dto.Result;
import me.dong.rest.service.CommentManager;

@RestController("commentController")
@RequestMapping(value = "/comment")
public class CommentController {
	
	@Autowired
	private CommentManager manager;

	/**
     * 댓글 저장
     *
     * @param comment 댓글
     *
     * @return 처리 결과
     */
	@RequestMapping(method = RequestMethod.POST)
	public Result insertCOmment(@RequestBody Comment comment){
		Result result = new Result();
		
		try{
			manager.saveComment(comment);
			
			result.setResult("ok");
		}catch(Exception e){
			e.printStackTrace();
			
			result.setResult("fail");
		}
		
		return result;
	}
	
	 /**
     * 댓글 목록 조회
     *
     * @param postId 포스트 시퀀스
     *
     * @return 댓글 목록
     */
	@RequestMapping(method = RequestMethod.GET)
	public Result getCommentList(@RequestParam(value = "postId") Long postId){
		Result result = new Result();
		
		try{
			List<Comment> commentList = manager.getCommentList(postId);
			
			result.setData(commentList);
			result.setResult("ok");
		}catch(Exception e){
			e.printStackTrace();
			
			result.setResult("fail");
		}
		
		return result;
	}
	
	/**
     * 댓글 삭제
     *
     * @param commentId 댓글 시퀀스
     *
     * @return 처리 결과
     */
	@RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
	public Result deleteComment(@PathVariable(value = "commentId") Long commentId){
		Result result = new Result();
		
		try{
			manager.deleteComment(commentId);
			result.setResult("ok");
		}catch(Exception e){
			e.printStackTrace();
			
			result.setResult("fail");
		}
		
		return result;
	}
}
