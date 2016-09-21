package me.dong.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dong.rest.dao.CommentDao;
import me.dong.rest.dto.Comment;

@Service(value = "commentManager")
public class CommentManager {
	
	@Autowired
	private CommentDao dao;
	
	public List<Comment> getCommentList(Long postId){
		return dao.getCommentList(postId);
	}
	
	@Transactional
	public void saveComment(Comment comment){
		dao.insertComment(comment);
	}
	
	public void deleteComment(Long commentId){
		dao.deleteComment(commentId);
	}
}
