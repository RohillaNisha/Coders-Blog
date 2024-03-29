package rasmus.nisha.codersblog.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    private String title;
    private String content;

    @CreationTimestamp
    private Date postDate;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;

    }

    private int owner;
}
