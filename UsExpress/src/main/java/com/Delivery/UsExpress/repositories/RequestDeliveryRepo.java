/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.RequestDelivery;

/**
 * @author Acer
 *
 */

@Repository
public interface RequestDeliveryRepo extends JpaRepository<RequestDelivery, Integer>{

}
