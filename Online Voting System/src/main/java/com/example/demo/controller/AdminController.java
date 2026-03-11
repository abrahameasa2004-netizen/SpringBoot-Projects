package com.example.demo.controller;

import com.example.demo.entity.Candidate;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.VoterRepository;  // ✅ IMPORT THIS
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;   // ✅ DECLARE HERE

    // ✅ Constructor Injection (UPDATED)
    public AdminController(CandidateRepository candidateRepository,
                           VoterRepository voterRepository) {
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;   // ✅ ASSIGN HERE
    }

    @GetMapping("/login")
    public String login() {
        return "admin-login";
    }
//from
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("candidates", candidateRepository.findAll());

        long totalVoters = voterRepository.count();
        long votedCount = voterRepository.countByVotedTrue();
        long totalCandidates = candidateRepository.count();

        model.addAttribute("totalVoters", totalVoters);
        model.addAttribute("votedCount", votedCount);
        model.addAttribute("totalCandidates", totalCandidates);

        return "admin-dashboard";
    }
//to    

    @PostMapping("/add-candidate")
    public String addCandidate(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "results";
    }

    // ✅ DELETE CANDIDATE
    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    // ✅ SHOW EDIT PAGE
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Candidate ID: " + id));

        model.addAttribute("candidate", candidate);
        return "edit-candidate";
    }

    // ✅ UPDATE CANDIDATE
    @PostMapping("/update/{id}")
    public String updateCandidate(@PathVariable Long id,
                                  @ModelAttribute Candidate updatedCandidate) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Candidate ID: " + id));

        candidate.setName(updatedCandidate.getName());
        candidate.setParty(updatedCandidate.getParty());

        candidateRepository.save(candidate);

        return "redirect:/admin/dashboard";
    }

    // ✅ VIEW VOTERS PAGE
    @GetMapping("/voters")
    public String viewVoters(Model model) {
        model.addAttribute("voters", voterRepository.findAll());
        return "voters";
    }
    
    
}