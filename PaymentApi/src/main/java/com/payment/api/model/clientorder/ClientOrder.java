package com.payment.api.model.clientorder;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.api.model.product.OrderProduct;
import com.payment.api.model.userclient.UserClient;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "client_order")
public class ClientOrder {
	@Transient
	@JsonIgnore
	private final double ivaPorcentaje = 0.19;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToMany(mappedBy = "clientOrder")
	private List<OrderProduct> orderProducts;

	@Column(name = "generation_date")
	private LocalDateTime generationDate;

	@Column(name = "delivery_cost")
	private Long deliveryCost;

	@Column(name = "total_cost")
	private Long totalCost;

	//The state can be Completed, In-Process, Cancelled
	@Column(name = "state")
	private String state;

	@ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private UserClient userClient;

	public void updateTotalCost(boolean withiva){
		long totalCost = 0;
		for(OrderProduct orderProduct : this.orderProducts)
		{
			totalCost += orderProduct.getCost();
		}
		long iva = 0;
		if(withiva)
			iva = (long) (totalCost*ivaPorcentaje);
		if(totalCost > 100000){
			this.deliveryCost = Long.valueOf(0);
		}
		this.totalCost = totalCost+deliveryCost+iva;
	}

	
}
