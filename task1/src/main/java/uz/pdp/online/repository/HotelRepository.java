package uz.pdp.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Boolean existsByName(String name);
}
