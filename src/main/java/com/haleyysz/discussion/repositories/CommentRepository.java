package com.haleyysz.discussion.repositories;

import com.haleyysz.discussion.models.Comment;
import com.haleyysz.discussion.models.Discussion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findAllByDiscussionId(String discussionId);
}
