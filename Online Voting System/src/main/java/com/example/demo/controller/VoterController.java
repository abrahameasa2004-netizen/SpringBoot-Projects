package com.example.demo.controller;

import com.example.demo.entity.Voter;
import com.example.demo.repository.VoterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VoterController {

    private final VoterRepository voterRepository;

    // ✅ Manual Constructor
    public VoterController(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @GetMapping("/")
    public String home() {
        return "register";
    }

    @PostMapping("/register")
    public String registerVoter(@ModelAttribute Voter voter) {

        if (voterRepository.existsByVoterId(voter.getVoterId())) {
            return "redirect:/?error";
        }

        voterRepository.save(voter);
        return "redirect:/?success";
    }

    @GetMapping("/check-voter-id")
    @ResponseBody
    public boolean checkVoterId(@RequestParam String voterId) {
        return voterRepository.existsByVoterId(voterId);
    }
}
