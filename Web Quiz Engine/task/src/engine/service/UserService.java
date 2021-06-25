package engine.service;

import engine.dto.UserDto;
import engine.model.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
     */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username);

        return userRepository.findByUsername(username);
    }

    // TODO добавить проверку по уникальности мыла
    public UserDto addUserToDB(UserDto userDto) {
        User u = convertUserDtoToUserEntity(userDto);
        //u.setPassword(bCryptPasswordEncoder.encode(u.getPassword())); // кодируем пароль
        userRepository.save(u);
        return convertUserEntityToUserDto(u);
    }

    private User convertUserDtoToUserEntity(UserDto dto) {
        User u = new User();

        u.setId(dto.getId());
        //u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setUsername(dto.getEmail());
        //u.setActive(true);

        return u;
    }

    private UserDto convertUserEntityToUserDto(User user) {
        UserDto u = new UserDto();

        u.setId(user.getId());
        //u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        //u.setUsername(user.getEmail());


        return u;
    }
}
