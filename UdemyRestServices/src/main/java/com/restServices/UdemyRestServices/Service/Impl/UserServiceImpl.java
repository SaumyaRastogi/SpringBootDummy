package com.restServices.UdemyRestServices.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restServices.UdemyRestServices.Service.UserService;
import com.restServices.UdemyRestServices.SharedDTO.UserDto;
import com.restServices.UdemyRestServices.SharedDTO.Utils;
import com.restServices.UdemyRestServices.UIModelResponse.ErrorMessages;
import com.restServices.UdemyRestServices.UIModelResponse.UserRest;
import com.restServices.UdemyRestServices.exceptions.UserServiceException;
import com.restServices.UdemyRestServices.io.Entity.UserEntity;
import com.restServices.UdemyRestServices.io.repositries.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		String publicUserId= utils.generateUserId(30);
		if(userRepository.findUserByEmail(user.getEmail())!=null) throw new RuntimeException("Already exist");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		System.out.println("password encoded" + bCryptPasswordEncoder.encode(user.getPassword()));
		
		userEntity.setUserId(publicUserId);
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}
	
	@Override 
	public UserDto getUser(String email)
	{
		UserEntity userEntity= userRepository.findUserByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
		
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity= userRepository.findUserByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		System.out.println("in authetication encrypted password filter"+ userEntity.getEncryptedPassword());
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
		
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue= new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserDto returnValue= new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		BeanUtils.copyProperties(userEntity, returnValue);
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		UserEntity updatedUserDetails =userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnValue= new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage= userRepository.findAll(pageableRequest);
		List<UserEntity> users= userPage.getContent();
		
		for(UserEntity userEntity:users)
		{
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userDto, userDto);
			returnValue.add(userDto);
			
		}
		return returnValue;
	}
}
