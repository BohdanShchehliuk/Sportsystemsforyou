package client.dto;

import client.entity.Client;
import client.entity.Logs;
import client.entity.Tariff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogsDto {

    private Client client;

    private Tariff tariff;
    private float payment;

    private Date startDay;

    private Date endDay;

    public Logs createLog() {

        return Logs.builder()
                .client(this.client)
                .tariff(this.tariff)
                .payment(this.payment)
                .startDay(this.startDay)
                .endDay(this.endDay)
                .build();
    }
}
