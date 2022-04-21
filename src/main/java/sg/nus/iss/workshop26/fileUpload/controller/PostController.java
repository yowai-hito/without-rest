package sg.nus.iss.workshop26.fileUpload.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import sg.nus.iss.workshop26.fileUpload.models.Post;
import sg.nus.iss.workshop26.fileUpload.repository.PostRepository;

@Controller
@RequestMapping(path="/post")
public class PostController {
    
    @Autowired
    private PostRepository postRepo;

    @GetMapping(path="/{postId}")
    public String getPostById(@PathVariable Integer postId, Model model){
        Optional<Post> opt= postRepo.getPostById(postId);
        byte[] img = opt.get().getImage();
        System.out.println(img);
        String imgsrc = "data:image/png;base64," + Base64.getEncoder().encodeToString(img);
        System.out.println(imgsrc);
        model.addAttribute("post", opt.get());
        model.addAttribute("imgsrc", imgsrc);
        return "post";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postPost(
        @RequestBody MultipartFile image,
        @RequestPart String comment, @RequestPart String poster, Model model
    ){
        String imageName = image.getOriginalFilename();
        long imageSize= image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];
        String encString = new String();

        try {
            buff = image.getBytes();
            encString = Base64.getEncoder().encodeToString(buff);
            buff = Base64.getDecoder().decode(encString);
        } catch (IOException e) {
            e.printStackTrace();
        }

      //  System.out.println(encString);
        encString = "";
        Post p = new Post();
        p.setComment(comment);
        p.setPoster(poster);
        p.setImageType(imageType);
        p.setImage(buff);
        p.setEncodedString(encString);

        Integer updateCount = postRepo.insertPost(p);
        model.addAttribute("updateCount",updateCount);

        return "result";
    }

}
