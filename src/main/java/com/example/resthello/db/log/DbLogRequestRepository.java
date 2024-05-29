package com.example.resthello.db.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbLogRequestRepository extends JpaRepository<DbLogRequestEntity, Long> {
}