package common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 9:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

//@TableName("t_teacher")
public class Teacher {

    @TableId
    private Integer id;

    private String teacherName;     //好像是这样写,就代表数据库的字段一定是teacher_name

    private String passWord;


}
