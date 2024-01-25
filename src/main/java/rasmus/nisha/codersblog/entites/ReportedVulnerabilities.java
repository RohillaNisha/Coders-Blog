package rasmus.nisha.codersblog.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ReportedVulnerabilities {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vulId;
    private String vulCategory;
    @NonNull
    private String description;
    @NonNull
    private String reportedBy;
    private String contactDetails;

}
