/**
 * 
 */
package com.Delivery.UsExpress.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Acer
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RequestDelivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	private String receiver_name;
	private String receiver_phone_no;
	//private String pickup_address;
	//private String destination_address;
	private double item_weight;
	private double distance;
	private double cost;
	
	@OneToOne(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pickupaddess_id" ,referencedColumnName = "pickUpAddressId")
	private PickUpAddress pickupaddress;
	
	@OneToOne(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "destinationaddress_id" ,referencedColumnName = "destinationAddressId")
	private DestinationAddress destinationaddress;
	
	@JsonIgnore
	@OneToOne(mappedBy = "requestDelivery")
	private ConfirmDelivery confirmDelivery;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;

}
