/**
 * 
 */
package com.Delivery.UsExpress.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Theikdi Sann
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Location {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int locationId;
	
	private double latitude;
	private double longitude;
	
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "location")
	private List<ConfirmDelivery> confirmDeliveryList;
}
