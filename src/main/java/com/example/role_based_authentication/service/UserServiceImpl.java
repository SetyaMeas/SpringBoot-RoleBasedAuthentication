package com.example.role_based_authentication.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.role_based_authentication.dto.CreateUserReq;
import com.example.role_based_authentication.dto.UserDto;
import com.example.role_based_authentication.model.RoleEnum;
import com.example.role_based_authentication.model.RoleModel;
import com.example.role_based_authentication.model.UserModel;
import com.example.role_based_authentication.repository.RoleRepo;
import com.example.role_based_authentication.repository.UserRepo;
import com.example.role_based_authentication.shared.ErrorCode;
import com.example.role_based_authentication.shared.Result;

import jakarta.transaction.Transactional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    @Transactional
    public Result<Boolean> create(CreateUserReq req) {
        Optional<UserModel> existedUser = userRepo.findByEmail(req.getEmail());
        if (existedUser.isPresent()) {
            return Result.Failure(ErrorCode.ALREADY_EXISTED, "Email already existed");
        }

        Optional<RoleModel> role = roleRepo.getByName(RoleEnum.ROLE_USER.name());
        if (role.isEmpty()) {
            return Result.Failure(ErrorCode.INTERNAL_SERVER_ERROR, "Role not found");
        }

        UserModel newUser = new UserModel();
        newUser.setName(req.getName());
        newUser.setEmail(req.getEmail());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));

        newUser.addRole(role.get());

        userRepo.save(newUser);
        return Result.Success(true);
    }

    @Override
    public Result<List<UserDto>> viewAll() {
        return Result.Success(userRepo.viewAll());
    }

    @Transactional
    @Override
    public Result<Boolean> grantAdmin(int userId) {
        Optional<UserModel> existedUser = userRepo.findById(userId);
        if (existedUser.isEmpty()) {
            return Result.Failure(ErrorCode.NOT_FOUND, "User id not found");
        }

        Optional<RoleModel> role = roleRepo.getByName(RoleEnum.ROLE_ADMIN.name());
        if (role.isEmpty()) {
            return Result.Failure(ErrorCode.INTERNAL_SERVER_ERROR, "Role not found");
        }

        Set<String> userExistedRoles = roleRepo.getUserRolesByUserId(userId);
        if (userExistedRoles.contains(RoleEnum.ROLE_ADMIN.name())) {
            return Result.Failure(ErrorCode.ALREADY_EXISTED, "User already has ADMIN Role");
        }

        UserModel user = existedUser.get();
        user.addRole(role.get());
        userRepo.save(user);

        return Result.Success(true);
    }
}
