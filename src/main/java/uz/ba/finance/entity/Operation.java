package uz.ba.finance.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.ba.finance.enums.Category;
import uz.ba.finance.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Bunyod on 04 июль 2023 at 23:06
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "operation", schema = "public")
public class Operation extends AbstractEntity {

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType type;
}
