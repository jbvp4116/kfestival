package com.leapteam.kfestival.dto;

import com.leapteam.kfestival.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

//롬복 종속성
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO { //회원 정보 필드
    private Long userCode;
    private String userEmail;
    private String userPW;
    private String userName;
    private String userNickName;
    private String userPhone;

    //어노테이션 사용으로 getter, setter, tostring, 생성자 생략
    public static UserDTO toUserDTO(UserEntity userEntity)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserCode(userEntity.getUserCode());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPW(userEntity.getUserPW());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserNickName(userEntity.getUserNickName());
        userDTO.setUserPhone(userEntity.getUserPhone());
    }
}
