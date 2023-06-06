package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="address", nullable = false)
    private String address;
    @Column(name="reference")
    private String reference;
    @Column(name="district", nullable = false)
    private String district;
    @Column(name="province", nullable = false)
    private String province;
    @Column(name="department", nullable = false)
    private String department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_CAMPAIGN_ASSIGNED_ID"))
    @JsonBackReference
    private Campaign campaign;
}
