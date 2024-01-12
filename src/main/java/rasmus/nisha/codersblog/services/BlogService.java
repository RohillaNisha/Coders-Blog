package rasmus.nisha.codersblog.services;

import org.springframework.stereotype.Service;
import rasmus.nisha.codersblog.dtos.CreateBlogDto;
import rasmus.nisha.codersblog.entites.Blog;
import rasmus.nisha.codersblog.repositories.BlogRepository;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public boolean addABlog(CreateBlogDto createBlogDto) {
        try {
            if (createBlogDto.getTitle() == null || createBlogDto.getTitle().isBlank() || createBlogDto.getTitle().isEmpty() ) {
                throw new IllegalArgumentException("Title can't be blank");
            }
            if (createBlogDto.getContent() == null || createBlogDto.getContent().isBlank() || createBlogDto.getContent().isEmpty()) {
                throw new IllegalArgumentException("Content can't be blank");
            }
            Blog newBlog = Blog.builder()
                    .title(createBlogDto.getTitle())
                    .content(createBlogDto.getContent())
                    .build();
            blogRepository.save(newBlog);
            return true;

        } catch(Exception e){
            e.printStackTrace();

        }

        return false;
    }


    public void deleteABlog(Integer blogId) {

        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Couldn't find Blog with this Id."));

        blogRepository.deleteById(blogId);
    }

    public void deleteAllBlogs() {

        blogRepository.deleteAll();
    }
}
