package com.restServices.UdemyRestServices.UIController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.UdemyRestServices.Service.UserService;
import com.restServices.UdemyRestServices.SharedDTO.UserDto;
import com.restServices.UdemyRestServices.UIModelRequest.UserDetailsRequestModel;
import com.restServices.UdemyRestServices.UIModelResponse.ErrorMessages;
import com.restServices.UdemyRestServices.UIModelResponse.OperationStatusModel;
import com.restServices.UdemyRestServices.UIModelResponse.RequestOperationName;
import com.restServices.UdemyRestServices.UIModelResponse.RequestOperationStatus;
import com.restServices.UdemyRestServices.UIModelResponse.UserRest;
import com.restServices.UdemyRestServices.exceptions.UserServiceException;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}",
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} ,
			consumes={MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} )
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		System.out.println(userDto.getUserId());
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

    @CrossOrigin(origins="http://localhost:4200")
	@PostMapping(
    consumes={MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} ,
	produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} 
    )
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		System.out.println(userDetails.toString());
		UserRest returnValue = new UserRest();
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("Null Exception");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);
		return returnValue;
	}
    


//	@PostMapping
//    public void createUser(@RequestBody UserDetailsRequestModel userDetails) {
//	System.out.println(userDetails.toString());y
//		
//	}
	@PutMapping(path="/{id}",
			consumes={MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} ,
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} 
		    )
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails)
			 {
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		return returnValue;
	}

	@DeleteMapping(path="/{id}",
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} 
		    )
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue= new OperationStatusModel(); 
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue; 
	}
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
			public List<UserRest> getUsers(@RequestParam(value="page", defaultValue="1" ) int page,
					@RequestParam(value="limit", defaultValue="25") int limit)
			{   List<UserRest> returnValue= new ArrayList<>();
			List<UserDto> users= userService.getUsers(page,limit);
			for(UserDto userDto:users)
			{
				UserRest userModel = new UserRest();
				BeanUtils.copyProperties(userDto, userModel);
				returnValue.add(userModel);
				
			}
				return returnValue;
			}

}
