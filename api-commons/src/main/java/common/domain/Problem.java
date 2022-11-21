package common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 9:08
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String content;

    private String photoName;

    private String time;

    @TableField(exist = false)//此字段在数据库中不存在
    private List<Comment> comments;


}
