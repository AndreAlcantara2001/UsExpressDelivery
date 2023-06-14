/**
 * 
 */
package com.Delivery.UsExpress.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
	private String name;
	private String email;
	private String password;
	
}
