package common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 14:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable { //序列化才能使用缓存


    private String id;

    private String teacherName;

    private String content;

    private Integer replyAll;

    private String time;

}
