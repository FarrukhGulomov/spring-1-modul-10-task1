package uz.pdp.online.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.online.entity.Hotel;
import uz.pdp.online.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @PostMapping()
    public String addHotel(@RequestBody Hotel hotel){
        Boolean isHotel = hotelRepository.existsByName(hotel.getName());
        if(isHotel) return "Hotel is already exists!";
        hotelRepository.save(hotel);
        return "Hotel is added!";
    }
    @GetMapping
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }
    @GetMapping("/byHotelId/{id}")
    public String getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(optionalHotel.isEmpty()) return "Hotel is not found!";
        return  optionalHotel.get().toString();
    }
}
