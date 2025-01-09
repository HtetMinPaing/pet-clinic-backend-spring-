package com.example.petclinicDB.controller;

import com.example.petclinicDB.domain.dto.UserDto;
import com.example.petclinicDB.domain.entity.UserEntity;
import com.example.petclinicDB.mapper.impl.UserMapper;
import com.example.petclinicDB.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/owner/")
public class UserController {

    private UserService userService;

    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "register")
    public ResponseEntity<UserDto> registerOwner(@RequestBody UserDto userDto) {
        UserEntity owner = userMapper.mapToEntity(userDto);
        UserEntity savedOwner = userService.registerOwner(owner);
        UserDto returnOwner = userMapper.mapToDto(savedOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.CREATED);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<UserDto> findOwner(@PathVariable("id") Integer id) {
        UserEntity foundOwner = userService.findOwnerById(id);
        UserDto returnOwner = userMapper.mapToDto(foundOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<UserDto> findOwnerByEmail(@RequestBody UserDto userDto) {
        UserEntity foundOwner = userService.findOwnerByEmail(userDto.getEmail());
        UserDto returnOwner = userMapper.mapToDto(foundOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @GetMapping(path = "/all/pages")
    public Page<UserDto> findAllPatients(
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String township
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<UserEntity> pages = userService.filterOwner(search, city, township, pageRequest);
        return pages.map(userMapper::mapToDto);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<UserDto> updateOwner(
            @PathVariable Integer id,
            @RequestBody UserDto userDto
    ) {
        UserEntity updatedOwner = userService.updateOwner(id, userDto);
        UserDto returnOwner = userMapper.mapToDto(updatedOwner);
        return new ResponseEntity<>(returnOwner, HttpStatus.FOUND);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Integer id) {
        String response = userService.deleteOwner(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
