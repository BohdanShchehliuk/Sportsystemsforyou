package client.dto;

import client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private String name;
    private String surname;
    private float wallet;
    private LocalDate birthdate;
    private String phone;
    private String additionalInfo;
    private boolean active;

    public Client createClient() {

        return Client.builder()
                .name(this.name)
                .surname(this.surname)
                .birthdate(this.birthdate)
                .wallet(this.wallet)
                .phone(this.phone)
                .active(this.active)
                .additionalInfo(this.additionalInfo)
                .build();
    }
}
