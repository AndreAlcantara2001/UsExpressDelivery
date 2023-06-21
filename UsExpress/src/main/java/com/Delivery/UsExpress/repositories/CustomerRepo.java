/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.Customer;

/**
 * @author Theikdi Sann
 *
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
