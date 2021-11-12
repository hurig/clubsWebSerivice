package hu.clubsWebService.controllers;

import hu.clubsWebService.domain.Club;
import hu.clubsWebService.services.ClubService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClubController {

    private final ClubService service;

    public ClubController(ClubService service) {
        this.service = service;
    }

    @GetMapping("/clubs")
    public List<Club> getClubs(){
        return service.getClubs();
    }
    @GetMapping("/clubs/{id}")
    public Club getClub(@PathVariable("id") Long id){
        return service.getClub(id);
    }

    @PostMapping("/clubs")
    @ResponseStatus(HttpStatus.CREATED)
    public Club addClub(@RequestBody Club club){
        return service.addClub(club);
    }

    @PatchMapping("/clubs/{id}/{guests}/{category}")
    public Club updateClub(@PathVariable("id") Long id,
                           @PathVariable("guests") int guests,
                           @PathVariable("category") int category){
        return service.updateClub(id, guests, category);
    }

    @PutMapping("/clubs/{id}")
    public Club replaceClub(@PathVariable("id") Long id, @RequestBody Club club){
        return service.replaceClub(id, club);
    }

    @RequestMapping(method=RequestMethod.DELETE, path="/clubs/{id}")
    public void deleteClub(@PathVariable("id") Long id){
        service.deleteClub(id);
    }
}
