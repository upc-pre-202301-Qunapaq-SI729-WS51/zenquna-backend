package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_USER_ORG_ID"))
    private User user;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="RUC", length = 11)
    private String RUC;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="web_page")
    private String webPage;
    @Column(name="logo")
    private String logo;

/*    private String RUC;
    private String phone;
    private String address;
    private String description;
    private String webPage;
    private String socialMedia;
    private String logo;*/
}
