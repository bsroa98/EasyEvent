package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLogRespository extends JpaRepository<ExceptionLog, Long> {

}
