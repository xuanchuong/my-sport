package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "password_reset_token")
public class PasswordResetTokenJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Player.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Player player;

    @Column(name = "expiry_date")
    private Date expiryDate;
}
