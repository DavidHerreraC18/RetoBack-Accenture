package com.payment.api.model.bill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.model.userclient.UserClient;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "bill")
public class Bill{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "generation_date")
    private LocalDateTime generationDate;

    @OneToOne(fetch = FetchType.EAGER)
    private ClientOrder clientOrder;

    @Column(name = "total_cost")
    private double totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserClient userClient;
    
}