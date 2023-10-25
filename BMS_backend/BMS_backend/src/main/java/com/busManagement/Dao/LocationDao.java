
package com.busManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.busManagement.entity.Location;
public interface LocationDao extends JpaRepository<Location, Long>{
}
