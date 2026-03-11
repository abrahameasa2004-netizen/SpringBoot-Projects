package com.example.demo.repository;

import com.example.demo.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Optional<Voter> findByVoterId(String voterId);

    boolean existsByVoterId(String voterId);
//    from
    long countByVotedTrue();
//    to
    List<Voter> findByVotedTrue();

    List<Voter> findByVotedFalse();
}