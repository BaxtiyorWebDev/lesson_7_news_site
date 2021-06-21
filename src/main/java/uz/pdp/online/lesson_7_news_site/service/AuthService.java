package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.entity.User;
import uz.pdp.online.lesson_7_news_site.exceptions.ResourceNotFoundException;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.RegisterDto;
import uz.pdp.online.lesson_7_news_site.repository.LavozimRepos;
import uz.pdp.online.lesson_7_news_site.repository.UserRepos;
import uz.pdp.online.lesson_7_news_site.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private LavozimRepos lavozimRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Parollar mos emas", false);

        if (userRepos.existsByUsername(registerDto.getUsername()))
            return new ApiResponse("Bunday username avval ro'xatdan o'tgan", false);

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepos.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("lavozim", "name", AppConstants.USER)),
                true
        );
        userRepos.save(user);
        return new ApiResponse("Muvaffaqqiyatli ro'yxatdan o'tdingiz", true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepos.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
