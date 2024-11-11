package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.data.DataHolder;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository {

    public List<Location> findAll(){
        return DataHolder.locations;
    }
}
