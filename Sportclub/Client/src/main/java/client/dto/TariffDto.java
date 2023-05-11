package client.dto;

import client.entity.Tariff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TariffDto {
    private String name;
    private int price;
    private int duration;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;

    private Time startHour;

    private Time endHour;
    private boolean active;

    public Tariff createTariff() {
        return Tariff.builder()
                .name(this.name)
                .price(this.price)
                .duration(this.duration)
                .monday(this.monday)
                .tuesday(this.tuesday)
                .wednesday(this.wednesday)
                .thursday(this.thursday)
                .friday(this.friday)
                .saturday(this.saturday)
                .sunday(this.sunday)
                .startHour(this.startHour)
                .endHour(this.endHour)
                .active(true)
                .active(true)
                .build();
    }
}
