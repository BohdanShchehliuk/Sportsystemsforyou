package client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT_INFO")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private float wallet;
    private LocalDate birthdate;

    private String phone;

    @Column (name = "ADDITIONAL_INFO")
    private String additionalInfo;
    private boolean active;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", wallet=" + wallet +
                ", birthdate=" + birthdate +
                ", phoneNumb='" + phone + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
