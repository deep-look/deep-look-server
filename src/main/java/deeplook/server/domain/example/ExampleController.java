package deeplook.server.domain.example;

import deeplook.server.domain.user.entity.Role;
import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final UserRepository userRepository;
    @Transactional
    @GetMapping("/api/v1/test")//테스트
    public String getSimplePosts() {
//        if (user != null) {
//            return user.getName();
//        }
        User user = User.builder()
                .name("helloo")
                .oid("3")
                .profileUrl("www.com")
                .role(Role.USER)
                .build();
        userRepository.save(user);
        System.out.println("hello");
        return "user가 존재하지않아요";
    }
}