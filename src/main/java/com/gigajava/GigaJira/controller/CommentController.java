package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.entity.Comment;
import com.gigajava.GigaJira.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment,
                          @RequestHeader("X-USER-ID") Long userId) {

        return commentService.create(comment, userId);
    }

    @GetMapping("/task/{taskId}")
    public List<Comment> getByTask(@PathVariable Long taskId,
                                   @RequestHeader("X-USER-ID") Long userId) {

        return commentService.getByTask(taskId, userId);
    }
}