/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.Admin;

/**
 * @author Theikdi Sann
 *
 */

@Repository
public interface AdminRepo extends  JpaRepository<Admin, Integer>{

}
