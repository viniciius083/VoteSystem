package com.vinicius.vs.vote.repository;

import com.vinicius.vs.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Long> {
}
