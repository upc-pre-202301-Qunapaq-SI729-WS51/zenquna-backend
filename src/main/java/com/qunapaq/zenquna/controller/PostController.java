package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Comment;
import com.qunapaq.zenquna.model.Donor;
import com.qunapaq.zenquna.model.Post;
import com.qunapaq.zenquna.model.Campaign;

import com.qunapaq.zenquna.repository.CommentRepository;
import com.qunapaq.zenquna.repository.DonorRepository;
import com.qunapaq.zenquna.repository.PostRepository;
import com.qunapaq.zenquna.repository.CampaignRepository;

import com.qunapaq.zenquna.exception.ValidationException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/zq/v1")
@CrossOrigin(origins = "*")
public class PostController {
    private final  PostRepository postRepository;
    private final  CampaignRepository campaignRepository;

    public PostController(PostRepository postRepository, CampaignRepository campaignRepository) {
        this.postRepository = postRepository;
        this.campaignRepository = campaignRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaign/{campaignId}/posts
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/campaign/{campaignId}/posts")
    public ResponseEntity<List<Post>> getPostsByCampaignId(@PathVariable Long campaignId) {
        List<Post> posts = postRepository.findByCampaign_Id(campaignId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaign/{campaignId}/posts
    //Method: POST
    @Transactional
    @PostMapping("/campaign/{campaignId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Long campaignId, @RequestBody Post post) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ValidationException("Campaign not found"));

        // Asignar la campaña al post
        post.setCampaign(campaign);

        return new ResponseEntity<Post>(postRepository.save(post), HttpStatus.CREATED);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaign/{campaignId}/posts/{postId}
    //Method: PUT
    @Transactional
    @PutMapping("/campaign/{campaignId}/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long campaignId, @PathVariable Long postId, @RequestBody Post post) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ValidationException("Campaign not found"));

        Post postToUpdate = postRepository.findById(postId)
                .orElseThrow(() -> new ValidationException("Post not found"));

        // Asignar la campaña al post
        postToUpdate.setCampaign(campaign);

        // Asignar los valores del post
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
        postToUpdate.setMultimedia(post.getMultimedia());
        postToUpdate.setDate(post.getDate());

        return new ResponseEntity<Post>(postRepository.save(postToUpdate), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaign/{campaignId}/posts/{postId}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/campaign/{campaignId}/posts/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long campaignId, @PathVariable Long postId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ValidationException("Campaign not found"));

        Post postToDelete = postRepository.findById(postId)
                .orElseThrow(() -> new ValidationException("Post not found"));

        // Asignar la campaña al post
        postToDelete.setCampaign(campaign);

        postRepository.delete(postToDelete);

        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
    }

}
