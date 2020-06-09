package com.restServices.UdemyRestServices.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restServices.UdemyRestServices.SharedDTO.UserDto;

public interface UserService extends UserDetailsService {
UserDto createUser( UserDto user);
UserDto getUser(String email);
UserDto getUserByUserId(String userId);
UserDto updateUser(String userId, UserDto user );
void deleteUser(String userId);
List<UserDto> getUsers(int page, int limit);
}
