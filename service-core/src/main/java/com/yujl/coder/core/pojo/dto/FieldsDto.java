package com.yujl.coder.core.pojo.dto;

import com.yujl.coder.core.pojo.entity.GenFields;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FieldsDto {
    private Long moduleId;
    private ArrayList<GenFields> fieldList;
}
