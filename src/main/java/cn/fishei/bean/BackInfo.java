package cn.fishei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("backInfo")

/**
 * 前端返回消息类
 */
public class BackInfo implements Serializable {
    private boolean flag;
    private Object data;
    private String errorMsg;

    public BackInfo(boolean flag){
        this.flag = flag;
    }

    public BackInfo(boolean flag, String errorMsg){
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
}
