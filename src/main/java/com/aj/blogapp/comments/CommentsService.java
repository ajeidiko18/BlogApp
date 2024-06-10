package com.aj.blogapp.comments;

import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
