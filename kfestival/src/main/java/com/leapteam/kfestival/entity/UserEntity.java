package com.leapteam.kfestival.entity;

import com.leapteam.kfestival.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;

    @Column(unique = true)
    private String userEmail;

    @Column
    private String userPW;

    @Column
    private String userName;

    @Column
    private String userNickName;

    @Column
    private String userPhone;

    public static UserEntity toUserEntity(UserDTO userDTO)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserCode(userDTO.getUserCode());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPW(userDTO.getUserPW());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserNickName(userDTO.getUserNickName());
        userEntity.setUserPhone(userDTO.getUserPhone());
    }
}
