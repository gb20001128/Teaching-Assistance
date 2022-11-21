package common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 9:09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String content;

    private Integer likes=0;

    private String time;


    private Integer pid;
}
