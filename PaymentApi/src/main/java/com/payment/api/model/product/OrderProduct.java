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

/**
 * Represents the order product.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_product")
public class OrderProduct{

    /**
     * Represents the unique identification of the OrderProduct.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    /**
     * Represents the product of the order.
     */
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    /**
     * Represents the quantity of product.
     */
    @Column(name = "quantity")
    private Long quantity;

    /**
     * Represents the cost of the product*quantity.
     */
    @Column(name = "cost")
    private Long cost;

    /**
     * Represents the order of the product.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private ClientOrder clientOrder;

    /**
     * Functions that updates the cost of the OrderProduct.
     */
    public void updateCost() {
        this.cost = this.product.getPrice() * this.quantity;
    }

}