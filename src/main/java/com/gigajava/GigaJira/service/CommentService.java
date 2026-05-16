package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.Comment;
import com.gigajava.GigaJira.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskService taskService;

    public CommentService(CommentRepository commentRepository,
                          TaskService taskService) {
        this.commentRepository = commentRepository;
        this.taskService = taskService;
    }

    public Comment create(Comment comment, Long userId) {

        taskService.get(comment.getTaskId(), userId);

        comment.setAuthorId(userId);

        return commentRepository.save(comment);
    }

    public List<Comment> getByTask(Long taskId, Long userId) {

        taskService.get(taskId, userId);

        return commentRepository.findByTaskId(taskId);
    }
}