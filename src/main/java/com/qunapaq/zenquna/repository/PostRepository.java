package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCampaign_Organization_Name(String name);

    List<Post> findByCampaign_Organization_Id(Long id);

    List<Post> findByCampaign_Id(Long id);
    List<Post> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
