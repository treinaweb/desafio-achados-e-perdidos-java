package br.com.treinaweb.achadosperdidos.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.api.dtos.requests.UserPostRequest;
import br.com.treinaweb.achadosperdidos.api.dtos.requests.UserPutRequest;
import br.com.treinaweb.achadosperdidos.api.mappers.UserMapper;
import br.com.treinaweb.achadosperdidos.core.models.User;
import br.com.treinaweb.achadosperdidos.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserPostRequest userPostRequest) {
        var user = userMapper.toModel(userPostRequest);
        setHashedPassword(user);
        return userRepository.save(user);
    }

    @Override
    public User updateById(Long userId, UserPutRequest userPutRequest) {
        var user = userRepository.findByIdOrElseThrow(userId);
        BeanUtils.copyProperties(userPutRequest, user);
        if (hasPasswordUpdate(userPutRequest)) {
            user.setSenha(userPutRequest.getPassword());
            setHashedPassword(user);
        }
        return userRepository.save(user);
    }

    private boolean hasPasswordUpdate(UserPutRequest userPutRequest) {
        return userPutRequest.getPassword() != null
            && userPutRequest.getPasswordConfirmation() != null;
    }

    private void setHashedPassword(User user) {
        var password = user.getSenha();
        var hashedPassword = passwordEncoder.encode(password);
        user.setSenha(hashedPassword);
    }

}
