package common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/21 15:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @TableId
    private Integer id;

    private String name;

    private Integer dataStructure;

    private Integer compositionPrinciple;

    private Integer dataBase;

    private Integer networkTechnique;

    private Integer operatingSystem;

    private Integer countGrade;

}
