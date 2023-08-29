package uz.pdp.online.payload;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.pdp.online.entity.Hotel;

@Data
public class RoomDto {

    private Integer number;
    private Integer floor;
    private Long size;
    private Integer hotelId;
}
