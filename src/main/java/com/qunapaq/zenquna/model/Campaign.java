package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="organization_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_ORGANIZATION_ID"))
    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    private Organization organization;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="slogan")
    private String slogan;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="goal", nullable = false)
    private double goal;
    @Column(name="collected", nullable = false)
    private double collected;
    @Column(name="start_date", nullable = false)
    private LocalDate startDate;
    @Column(name="end_date", nullable = false)
    private LocalDate endDate;
    @Column(name="status", nullable = false)
    private String status;
    @Column(name="header_image", nullable = false)
    private String headerImage;
    @Column(name="body_image")
    private String bodyImage;
    /* add one or many locations for a one campaign*/
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Location> locations;
}
