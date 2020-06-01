package cn.fishei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias("user")

/**
 * 用户类
 */
public class User {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String status;
    private String uuid;

}
