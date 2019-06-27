package com.haleyysz.discussion.services;

import com.haleyysz.discussion.models.Course;
import com.haleyysz.discussion.models.Discussion;
import com.haleyysz.discussion.repositories.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionService {
	@Autowired
	private DiscussionRepository discussionRepository;

	public Discussion find(String id ) {
		return discussionRepository.findOne(id);
	}

	public List<Discussion> findAll( ) {
		return discussionRepository.findAll();
	}

	public Discussion save(Discussion discussion){
		return discussionRepository.save(discussion);
	}

	public List<Discussion> findByCourse(String course) { return discussionRepository.findByCourse(course); }

	public Discussion createThing( Discussion t ) {
		return discussionRepository.save( t );
	}
	public void delete(String id){
		discussionRepository.delete(id);
	}

}
