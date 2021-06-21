package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.UserDto;
import uz.pdp.online.lesson_7_news_site.repository.UserRepos;

@Service
public class UserService {

    @Autowired
    UserRepos userRepos;

    public ApiResponse addUser(UserDto userDto) {
        boolean existsByUsername = userRepos.existsByUsername(userDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("Bunday username mavjud",false);

        return null;
    }
}
