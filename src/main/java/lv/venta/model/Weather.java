package lv.venta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Weather {
    private String description;
    private int temperature;
    private String lastUpdated;

}