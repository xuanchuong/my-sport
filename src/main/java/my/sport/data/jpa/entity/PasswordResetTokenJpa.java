package my.sport.data.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class PasswordResetTokenJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = JpaPlayer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private JpaPlayer player;

    private Date expiryDate;
}
