package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Comment;
import com.qunapaq.zenquna.model.Donor;
import com.qunapaq.zenquna.repository.CommentRepository;
import com.qunapaq.zenquna.repository.OrganizationRepository;
import com.qunapaq.zenquna.repository.DonorRepository;
import com.qunapaq.zenquna.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentRepository commentRepository;
    private final DonorRepository donorRepository;

    public CommentController(CommentRepository commentRepository, DonorRepository donorRepository) {
        this.commentRepository = commentRepository;
        this.donorRepository = donorRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors/{donorId}/comments
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donors/{donorId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByDonorId(@PathVariable Long donorId) {
        List<Comment> comments = commentRepository.findByDonorId(donorId);
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors/{donorId}/comments
    //Method: POST
    @Transactional
    @PostMapping("/donors/{donorId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long donorId, @RequestBody Comment comment) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new ValidationException("Donor not found"));

        comment.setDonor(donor);
        return new ResponseEntity<Comment>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    /*//EndPoint: http://localhost:8080/api/zq/v1/comments/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Comment not found"));
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }*/

    //EndPoint: http://localhost:8080/api/zq/v1/comments/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment commentToUpdate = commentRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Comment not found"));

        commentToUpdate.setContent(comment.getContent());
        commentToUpdate.setPost(comment.getPost());
        commentToUpdate.setDonor(comment.getDonor());

        return new ResponseEntity<Comment>(commentRepository.save(commentToUpdate), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/comments/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Comment not found"));
        commentRepository.delete(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
}
