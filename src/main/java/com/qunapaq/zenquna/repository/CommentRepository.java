package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByDonorId(String donorId);
    List<Comment> findByPostId(Long postId);
    List<Comment> findByDate(String date);
}
