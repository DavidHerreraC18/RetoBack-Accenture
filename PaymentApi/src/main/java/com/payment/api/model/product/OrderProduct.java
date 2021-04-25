package com.payment.api.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.api.model.clientorder.ClientOrder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_product")
public class OrderProduct{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "cost")
    private Long cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private ClientOrder clientOrder;

    public void updateCost() {
        this.cost = this.product.getPrice() * this.quantity;
    }

}