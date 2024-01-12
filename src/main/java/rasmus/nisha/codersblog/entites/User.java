package rasmus.nisha.codersblog.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_Users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;


}
