package cn.fishei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("tlike")
/**
 * 用户收藏类
 */
public class Tlike {
    private int uid;
    private int tid;
    private Date date;

}
