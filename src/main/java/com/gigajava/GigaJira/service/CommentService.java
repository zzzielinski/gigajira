package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.CommentCreateRequest;
import com.gigajava.GigaJira.entity.Comment;
import com.gigajava.GigaJira.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Comment create(CommentCreateRequest request, Long userId) {

        taskService.get(request.getTaskId(), userId);

        Comment comment = new Comment();
        comment.setTaskId(request.getTaskId());
        comment.setContent(request.getContent());
        comment.setAuthorId(userId);

        return commentRepository.save(comment);
    }

    public List<Comment> getByTask(Long taskId, Long userId) {

        taskService.get(taskId, userId);

        return commentRepository.findByTaskId(taskId);
    }

    public Comment update(Long commentId, Long userId, String content) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        if (!comment.getAuthorId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the author can edit this comment");
        }

        comment.setContent(content);
        return commentRepository.save(comment);
    }
}