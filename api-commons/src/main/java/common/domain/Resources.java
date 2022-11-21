package common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 12:17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resources {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private  String teacherName;

    private String resourcesName;

    private String time;


}
