package uz.pdp.online.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.entity.Hotel;
import uz.pdp.online.entity.Room;
import uz.pdp.online.payload.RoomDto;
import uz.pdp.online.repository.HotelRepository;
import uz.pdp.online.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;

    public RoomController(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
private static final Integer PAGE_SIZE =10;
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if(optionalHotel.isEmpty()) return "Hotel with "+ roomDto.getHotelId() + " id is not found!";
        Boolean existRoom = roomRepository.existsRoomByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if(existRoom) return roomDto.getNumber()+" number of room is already exists!";
        Room room=new Room(null, roomDto.getNumber(), roomDto.getFloor(),
                roomDto.getSize(), optionalHotel.get());
        roomRepository.save(room);
        return room.getNumber()+" number is added in room!";
    }
@GetMapping("getRoomsByHotelIdByPage/{hotelId}")
    public Page<Room> getRoomsByHotelIdByPage(@PathVariable Integer hotelId,@RequestParam Integer page){
    Pageable pageable= PageRequest.of(page,PAGE_SIZE);

    return roomRepository.getRoomsByHotelId(hotelId,pageable);
}
}
