package com.haleyysz.discussion.services;

import com.haleyysz.discussion.models.Comment;
import com.haleyysz.discussion.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }
    public List<Comment> findByDiscussionId(String id){
        return commentRepository.findAllByDiscussionId(id);
    }
}
