package hu.clubsWebService.repositories;

import hu.clubsWebService.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    public List<Club> findAllByOrderByName();
}
