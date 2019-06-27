package com.haleyysz.discussion.repositories;

import com.haleyysz.discussion.models.Discussion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {

    public List<Discussion> findByCourse(String course);
}
