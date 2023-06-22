package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="donor_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_DONOR_COMMENT_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Donor donor;
    @ManyToOne
    @JoinColumn(name="post_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_POST_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post post;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="date", nullable = false)
    private String date;
}
