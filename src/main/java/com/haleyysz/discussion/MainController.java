package com.haleyysz.discussion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haleyysz.discussion.models.*;
import com.haleyysz.discussion.services.CommentService;
import com.haleyysz.discussion.services.DiscussionService;
import com.haleyysz.discussion.services.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    DiscussionService discussionService;

    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public @ResponseBody
    User loginPost(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") int type, HttpSession session, HttpServletResponse response) {
        User user = userService.findUser(username);
        if (user != null&& type == user.getType()) {
            if (user.getPassword().equals(password)) {
                session.setAttribute(WebSecurityConfig.SESSION_KEY, user);
                return user;
            }
        }
        return null;
    }

    @GetMapping("/discussion")
    public @ResponseBody List<Discussion> discussionGet(@RequestParam("course") String course){
        return discussionService.findByCourse(course);

    }
    @DeleteMapping("/discussion/{id}")
    public @ResponseBody Msg deleteDiscussion(@PathVariable("id") String id){
        discussionService.delete(id);
        return new Msg("ok");


    }
    @PostMapping("/discussion")
    public @ResponseBody Discussion discussionPost(@RequestBody Discussion discussion,HttpSession session){
        User user=(User) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        discussion.setUser(user);
        discussion.setDate(System.currentTimeMillis()+"");
        return discussionService.save(discussion);
    }
    @PostMapping("/reply")
    public @ResponseBody
    Comment sendComment(@RequestBody Comment comment,HttpSession session){
        comment.setDate(System.currentTimeMillis());
        comment.setUser((User)session.getAttribute(WebSecurityConfig.SESSION_KEY));
        return commentService.save(comment);
    }
    @GetMapping("/{discussionId}/comment")
    public @ResponseBody
    List<Comment> getComment(@PathVariable("discussionId") String id){

        return commentService.findByDiscussionId(id);
    }
    @GetMapping("/test")
    public @ResponseBody
    String create() {
        User user0 = new User();
        user0.setType(User.TYPE_INSTRUCTOR);
        user0.setUsername("instructor");
        user0.setPassword("123456");
        List<Course> list=new ArrayList<>();
        String arr[]={"CS 120 - Java","CS 741 - Software Engineering", "CS 743 - Software Verification and Validation", "CS 451/551 - User Interface Design"};
        list.add(new Course(arr[0]));
        list.add(new Course(arr[1]));
        list.add(new Course(arr[2]));
        list.add(new Course(arr[3]));
        user0.setCourse(list);
        userService.save(user0);

        User user1 = new User();
        user1.setType(User.TYPE_BEGINNER);
        user1.setUsername("beginner");
        user1.setPassword("123456");
        list=new ArrayList<>();
        list.add(new Course(arr[0]));
        user1.setCourse(list);
        userService.save(user1);

        User user2 = new User();
        user2.setType(User.TYPE_INTERMEDIATE);
        user2.setUsername("intermediate");
        user2.setPassword("123456");
        list=new ArrayList<>();
        list.add(new Course(arr[0]));
        list.add(new Course(arr[1]));
        user2.setCourse(list);
        user2.setCourse(list);
        userService.save(user2);

        User user3 = new User();
        user3.setType(User.TYPE_EXPERT);
        user3.setUsername("expert");
        user3.setPassword("123456");
        list=new ArrayList<>();
        list.add(new Course(arr[0]));
        list.add(new Course(arr[1]));
        list.add(new Course(arr[2]));
        list.add(new Course(arr[3]));
        user3.setCourse(list);
        userService.save(user3);
        return "hi";

    }
    @RequestMapping(value = "images/{id}", method = RequestMethod.GET)
    public void getAvatar(@PathVariable("id") String id, HttpServletResponse res) {
        File file = null;
        FileInputStream fis = null;
        String FILE_PATH="/Users/haleyysz/Desktop/";
        File file1 = new File( FILE_PATH, id+".png");
        File file2 = new File(FILE_PATH, id + ".jpg");
        if (file1.exists()) {
            file = file1;
        }
        if (file2.exists()) {
            file = file2;
        }
        if (file != null) {
            try {
                fis = new FileInputStream(file);
                byte[] b = new byte[fis.available()];
                fis.read(b);
                String fileType = new MimetypesFileTypeMap().getContentType(file);
                res.setContentType(fileType);
                OutputStream out = res.getOutputStream();
                out.write(b);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            try {
                res.getWriter().println("Avatar does not exists");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @GetMapping("/user")
    public @ResponseBody User currentUser(HttpSession session) {
        // 移除session
        if(session.getAttribute(WebSecurityConfig.SESSION_KEY)!=null){
            return (User)session.getAttribute(WebSecurityConfig.SESSION_KEY);
        }
        return null;
    }
    @GetMapping("/logout")
    public @ResponseBody
    Msg logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return new Msg("ok");
    }

}
