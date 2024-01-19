package rasmus.nisha.codersblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasmus.nisha.codersblog.entites.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContaining(String value);
    List<Blog> findByContentContaining(String value);
}
