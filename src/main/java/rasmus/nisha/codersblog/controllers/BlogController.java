package rasmus.nisha.codersblog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rasmus.nisha.codersblog.dtos.CreateBlogDto;
import rasmus.nisha.codersblog.entites.Blog;
import rasmus.nisha.codersblog.services.BlogService;

import java.util.List;

@RestController
@RequestMapping("api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> result = blogService.getAllBlogs();
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addABlog(@RequestBody CreateBlogDto createBlogDto) {

        boolean result = blogService.addABlog(createBlogDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Blog successfully posted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body("Something went wrong.");
        }

    }

    @DeleteMapping("/{blogId}/delete")
    public ResponseEntity<String> deleteABlog(@PathVariable Integer blogId) {
        if (blogId == null) {
            return ResponseEntity.badRequest().body("BlogId cannot be null");

        }
        blogService.deleteABlog(blogId);
        return ResponseEntity.ok("Blog deleted.");
    }


    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll() {
    blogService.deleteAllBlogs();
    return ResponseEntity.status(HttpStatus.CREATED).body("Blogs successfully deleted.");

        /*if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Blogs successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body("Something went wrong. Are you even admin??");
        }*/

    }



}
