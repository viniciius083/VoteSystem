package com.vinicius.vs.vote.service;

import com.vinicius.vs.vote.dtos.VoteConfirmationDTO;
import com.vinicius.vs.vote.dtos.VoteDTO;

public interface IVoteService {
    VoteConfirmationDTO vote(VoteDTO voteDTO);
}
