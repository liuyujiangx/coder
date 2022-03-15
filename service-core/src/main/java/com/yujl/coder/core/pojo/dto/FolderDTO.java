package com.yujl.coder.core.pojo.dto;

import com.yujl.coder.core.pojo.entity.GenFolder;
import lombok.Data;

import java.util.List;

/**
 * @author liuyj
 */
@Data
public class FolderDTO {
    private Long id;

    private Long pid;

    private String label;

    private Boolean file;

    private List<FolderDTO> children;
}
