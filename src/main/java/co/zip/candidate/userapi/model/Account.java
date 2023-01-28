package co.zip.candidate.userapi.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "accounts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @Column(nullable = false, unique = true)
  private String accountNumber;

  @Column(nullable = false)
  private BigDecimal deposit;

  @Column(nullable = false)
  private BigDecimal credits;

  @Column(nullable = false)
  private String billingAddress;

  @Column(nullable = false)
  private String contactNumber;

  @Column(nullable = false)
  @Builder.Default
  private Currency currency = Currency.getInstance("AUD");

  @Column(nullable = false)
  @Builder.Default
  private boolean validity = true;

  @CreationTimestamp
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  private OffsetDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
