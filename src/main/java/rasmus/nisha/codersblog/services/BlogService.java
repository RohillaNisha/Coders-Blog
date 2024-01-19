package rasmus.nisha.codersblog.services;

import org.springframework.stereotype.Service;
import rasmus.nisha.codersblog.dtos.CreateBlogDto;
import rasmus.nisha.codersblog.entites.Blog;
import rasmus.nisha.codersblog.entites.Role;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.BlogRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public boolean addABlog(CreateBlogDto createBlogDto, User user) {
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
                    .owner(user.getId())
                    .build();
            blogRepository.save(newBlog);
            return true;

        } catch(Exception e){
            e.printStackTrace();

        }

        return false;
    }


    public void deleteABlog(Integer blogId, User user) throws AccessDeniedException {

        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Couldn't find Blog with this Id."));

        if( blog.getOwner() != user.getId()  && user.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            throw new AccessDeniedException("You can only delete your own blogs.");
        }

        blogRepository.deleteById(blogId);
    }

    public void deleteAllBlogs(User user) {

        blogRepository.deleteAll();
    }

    public List<Blog> getUsersOwnBlogs(int owner) {
        return blogRepository.findAllByOwner(owner);
    }

    public Blog getBlogById(Integer blogId) {

        return blogRepository.findById(blogId).orElseThrow(() -> new NoSuchElementException("Couldn't find Blog with this Id."));
    }

    public List<Blog> searchBlogs(String value) {
        var result = new ArrayList<Blog>();

        var searchTitle = this.blogRepository.findByTitleContaining(value);
        var searchContent = this.blogRepository.findByContentContaining(value);

        for(var blog : searchTitle){
            if (result.stream().noneMatch(any -> any.getBlogId().equals(blog.getBlogId()))){
                result.add(blog);
            }
        }

        for(var blog : searchContent){
            if (result.stream().noneMatch(any -> any.getBlogId().equals(blog.getBlogId()))){
                result.add(blog);
            }
        }

        return result;
    }
}
