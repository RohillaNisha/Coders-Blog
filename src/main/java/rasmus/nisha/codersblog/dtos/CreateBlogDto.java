package rasmus.nisha.codersblog.dtos;
import lombok.Data;

import java.util.Date;

@Data
public class CreateBlogDto {
    private String title;
    private String content;

}
