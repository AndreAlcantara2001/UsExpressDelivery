/**
 * 
 */
package com.Delivery.UsExpress.entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;


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
public class Rider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int riderId;
	
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "rider")
	private List<Location> riderLocation;

}
