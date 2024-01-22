package rasmus.nisha.codersblog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rasmus.nisha.codersblog.dtos.CreateBlogDto;
import rasmus.nisha.codersblog.entites.Blog;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.services.BlogService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("api/blog")
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
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

    @GetMapping("/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Integer blogId) {
        Blog result = blogService.getBlogById(blogId);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/my-blogs")
    public ResponseEntity<List<Blog>> getUsersBlogs(@AuthenticationPrincipal User user) {
        List<Blog> result = blogService.getUsersOwnBlogs(user.getId());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addABlog(@RequestBody CreateBlogDto createBlogDto , @AuthenticationPrincipal User user ) {

        boolean result = blogService.addABlog(createBlogDto, user);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Blog successfully posted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body("Something went wrong.");
        }

    }



    @DeleteMapping("/{blogId}/delete")
    public ResponseEntity<String> deleteABlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) throws AccessDeniedException {
        if (blogId == null) {
            return ResponseEntity.badRequest().body("BlogId cannot be null");

        }
        blogService.deleteABlog(blogId, user);
        return ResponseEntity.ok("Blog deleted.");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll(@AuthenticationPrincipal User user) {
    blogService.deleteAllBlogs(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Blogs successfully deleted.");

        /*if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Blogs successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body("Something went wrong. Are you even admin??");
        }*/

    }

    @GetMapping("/search/{value}")
    public ResponseEntity<List<Blog>> searchBlogs(@PathVariable String value){
        return ResponseEntity.ok(this.blogService.searchBlogs(value));
    }





}
