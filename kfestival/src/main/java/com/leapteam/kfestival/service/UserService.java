package com.leapteam.kfestival.service;

import com.leapteam.kfestival.dto.UserDTO;
import com.leapteam.kfestival.entity.UserEntity;
import com.leapteam.kfestival.repositiory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    //JPA, MySQL 종속
    private final UserRepository userRepository;

    public void save(UserDTO userDTO)
    {
        UserEntity userEntity = UserEntity.toUserEntity(UserDTO);
        userRepository.save(userEntity);
    }

    public UserDTO login(UserDTO userDTO)
    {
        Optional<UserEntity> byUserEmail = UserRepository.findByUserEmail(userDTO.getUserEmail());
        if(byUserEmail.isPresent()) //조회결과 있음
        {
            UserEntity userEntity = byUserEmail.get();

            if (userEntity.getUserPW().equals(userDTO.getUserPW())) //비밀번호 일치
            {
                UserDTO dto = UserDTO.toUserDTO(userEntity); //entity -> DTO 변환 후 리턴
                return dto;
            }

            else //비밀번호 불일치
            {
                return null;
            }
        }

        else //조회결과 없음
        {
            return null;
        }
    }
}
