package uz.ba.finance.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import uz.ba.finance.criteria.OperationCriteria;
import uz.ba.finance.entity.Operation;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Bunyod on 07 июль 2023 at 11:18
 */

@Component
public class OperationSpecification {
    public Specification<Operation> getSpecification(OperationCriteria criteria) {
        return typeSpecification(criteria.getType())
                .and(categorySpecification(criteria.getCategory()))
                .and(dateSpecification(criteria.getCreationDate()));
    }

    private Specification<Operation> typeSpecification(String type) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(type)
                .map(i -> criteriaBuilder.like(root.get("type"), likeFormat(i)))
                .orElse(null);
    }

    private Specification<Operation> categorySpecification(String category) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(category)
                .map(i -> criteriaBuilder.like(root.get("category"), likeFormat(i)))
                .orElse(null);
    }

    private Specification<Operation> dateSpecification(LocalDateTime dateTime) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(dateTime)
                .map(i -> criteriaBuilder.equal(root.get("creationDate"), i))
                .orElse(null);
    }

    public static String likeFormat(String value) {
        return MessageFormat.format("%{0}%", value);
    }
}
