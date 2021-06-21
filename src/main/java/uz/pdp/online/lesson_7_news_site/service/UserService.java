package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.entity.User;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.UserDto;
import uz.pdp.online.lesson_7_news_site.repository.LavozimRepos;
import uz.pdp.online.lesson_7_news_site.repository.UserRepos;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private LavozimRepos lavozimRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse addUser(UserDto userDto) {
        boolean existsByUsername = userRepos.existsByUsername(userDto.getUsername());
        if (existsByUsername)
            return new ApiResponse("Bunday username mavjud",false);
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setLavozim(lavozimRepos.getById(userDto.getLavozimId()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepos.save(user);
        return null;
    }


    public ApiResponse editUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepos.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("Foydalanuvchi topilmadi",false);

        User editingUser = optionalUser.get();
        editingUser.setFullName(userDto.getFullName());
        editingUser.setUsername(userDto.getUsername());
        editingUser.setLavozim(lavozimRepos.getById(userDto.getLavozimId()));
        editingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepos.save(editingUser);
        return new ApiResponse("Ma'lumotlar tahrirlandi",true);
    }

    public Page<User> getPageUsers(int page) {
        Pageable pageable = PageRequest.of(page,20);
        return userRepos.findAll(pageable);
    }


    public ApiResponse deleteUserById(Long id) {
        try {
            userRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi",true);
        } catch (UsernameNotFoundException e) {
            return new ApiResponse("User topilmadi",false);
        }
    }
}
