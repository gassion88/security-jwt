package com.gassion88.security.jwt.service.implementation;

import com.gassion88.security.jwt.dto.JwtAuthRequestDTO;
import com.gassion88.security.jwt.dto.JwtAuthResponseDTO;
import com.gassion88.security.jwt.entity.User;
import com.gassion88.security.jwt.repository.RoleRepository;
import com.gassion88.security.jwt.repository.UserRepository;
import com.gassion88.security.jwt.service.AuthService;
import com.gassion88.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map( role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Override
    public User saveUser(User user) {
        if (findByUsername(user.getUsername()).isPresent()) {
            System.out.println("this user is already registered");
        }

        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthResponseDTO createAuthToken(JwtAuthRequestDTO jwtAuthRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtAuthRequestDTO.getUsername(), jwtAuthRequestDTO.getPassword()));
        UserDetails userDetails = loadUserByUsername(jwtAuthRequestDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtAuthResponseDTO(token);
    }
}
