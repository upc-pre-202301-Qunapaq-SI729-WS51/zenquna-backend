package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="campaign_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_CAMPAIGN_POST_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Campaign campaign;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="multimedia", nullable = false)
    private String multimedia;
    @Column(name="date", nullable = false)
    private LocalDate date;
}
