package client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT_LOGS")

public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TARIFF_PLAN_ID")
    private Tariff tariff;
    private float payment;
    @Column(name = "DAY_START_TARIFF")
    private Date startDay;
    @Column(name = "DAY_END_TARIFF")
    private Date endDay;
    private boolean active;

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", client=" + client.getSurname() + " " + client.getName() +
                ", tariff=" + tariff.getName() +
                ", payment=" + payment +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", active=" + active +
                '}';
    }
}
