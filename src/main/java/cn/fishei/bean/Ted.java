package cn.fishei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ted")

/**
 * Ted视频类
 */
public class Ted {
    private int tid;
    private String title;
    private String discription;
    private String mp4ShdUrl;
}
