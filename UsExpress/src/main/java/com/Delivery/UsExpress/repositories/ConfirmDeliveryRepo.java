/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.ConfirmDelivery;

/**
 * @author Theikdi Sann
 *
 */

@Repository
public interface ConfirmDeliveryRepo extends JpaRepository<ConfirmDelivery, Integer>{

}
