package com.ucatolica.easyevent.easyevent.repositories;
import com.ucatolica.easyevent.easyevent.model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long> {
}
