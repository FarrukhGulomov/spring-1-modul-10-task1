package uz.pdp.online.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Boolean existsRoomByNumberAndHotelId(Integer number,Integer hotelId);

    Page<Room> getRoomsByHotelId(Integer hotelId, Pageable pageable);
}
