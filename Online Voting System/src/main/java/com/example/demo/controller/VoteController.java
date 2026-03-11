package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VoteController {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    // ✅ Manual Constructor
    public VoteController(VoterRepository voterRepository,
                          CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/vote")
    public String votePage() {
        return "vote";
    }

    @PostMapping("/vote")
    public String submitVote(@RequestParam String voterId,
                             @RequestParam Long candidateId,
                             Model model) {

        Voter voter = voterRepository.findByVoterId(voterId).orElse(null);

        // ✅ FIXED LINE
        if (voter == null || voter.isVoted()) {
            model.addAttribute("error", "Invalid Voter ID or Already Voted!");
            return "vote";
        }

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

        if (candidate != null) {

            candidate.setVoteCount(candidate.getVoteCount() + 1);

            // ✅ FIXED LINE
            voter.setVoted(true);

            candidateRepository.save(candidate);
            voterRepository.save(voter);

            model.addAttribute("success", "Vote submitted successfully!");
        } else {
            model.addAttribute("error", "Invalid Candidate!");
        }

        return "vote";
        
//        @GetMapping("/back")
    }
}
