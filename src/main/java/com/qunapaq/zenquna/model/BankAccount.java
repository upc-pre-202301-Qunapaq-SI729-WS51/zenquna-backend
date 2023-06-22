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
@Table(name="bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="bank", nullable = false)
    private String bank;
    @Column(name="account_number", nullable = false)
    private String accountNumber;
    /*Add a foreign key of campaign id*/
    @ManyToOne
    @JoinColumn(name="campaign_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_CAMPAIGN_ID"))
    @JsonProperty("campaign_id")
    private Campaign campaign;
}
