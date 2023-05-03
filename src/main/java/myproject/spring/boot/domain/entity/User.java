package myproject.spring.boot.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import org.hibernate.annotations.UuidGenerator;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.JdbcTypeCode;
import java.util.UUID;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "federated_user_id")
    private String federatedUserId;
}
