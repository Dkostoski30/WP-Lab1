package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {

    public List<Location> findAll();
    Optional<Location> findById(Long id);
}
