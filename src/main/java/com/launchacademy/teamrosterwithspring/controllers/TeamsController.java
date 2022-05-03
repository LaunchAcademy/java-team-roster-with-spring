package com.launchacademy.teamrosterwithspring.controllers;

import com.launchacademy.teamrosterwithspring.models.League;
import com.launchacademy.teamrosterwithspring.models.Team;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/")
public class TeamsController {

  @GetMapping
  public String getTeamsList(Model model) {
    League league = League.getLeague();
    List<Team> teamsList = league.getTeams();
    model.addAttribute("teamsList", teamsList);
    return "teams/index";
  }

  @GetMapping("/teams/{id}")
  public String getTeam(@PathVariable Integer id, Model model) {
    List<Team> teamsList = League.getLeague().getTeams();
    if (id >= teamsList.size() || id < 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("team", teamsList.get(id));
    return "teams/show";
  }
}
