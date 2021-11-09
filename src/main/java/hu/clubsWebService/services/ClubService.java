package hu.clubsWebService.services;

import hu.clubsWebService.domain.Club;
import hu.clubsWebService.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository repository;

    public List<Club> getClubs() {
        return repository.findAllByOrderByName();
    }

    public Club getClub(Long id) {
        Optional<Club> club = repository.findById(id);
        if(club.isPresent())
            return club.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
