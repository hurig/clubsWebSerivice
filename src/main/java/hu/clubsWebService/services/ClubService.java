package hu.clubsWebService.services;

import hu.clubsWebService.domain.Category;
import hu.clubsWebService.domain.Club;
import hu.clubsWebService.repositories.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Club> getClubs() {
        return repository.findAllByOrderByName();
    }

    public Club getClub(Long id) {
        Optional<Club> club = repository.findById(id);
        if(club.isPresent())
            return club.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Club addClub(Club club) {
        if(isUnique(club.getName()))
            return repository.save(club);
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    private boolean isUnique(String name) {
        List<Club> clubs = repository.findAll();
        for (Club club : clubs) {
            if(club.getName().equals(name))
                return false;
        }
        return true;
    }

    public Club updateClub(Long id, int guests, int category) {
        Optional<Club> optionalClub = repository.findById(id);
        if(optionalClub.isPresent()){
            Club updatingClub = optionalClub.get();
            updatingClub.setGuests(guests);
            Optional<Category> optionalCategory = categoryRepository.findById(category);
            if(optionalCategory.isPresent()){
                updatingClub.setCategory(optionalCategory.get());
                return repository.save(updatingClub);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Club replaceClub(Long id, Club club) {
        Optional<Club> optionalClub = repository.findById(id);
        if(optionalClub.isPresent()){
            club.setId(id);
            return repository.save(club);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteClub(Long id) {
        Optional<Club> optionalClub = repository.findById(id);
        if(!optionalClub.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
