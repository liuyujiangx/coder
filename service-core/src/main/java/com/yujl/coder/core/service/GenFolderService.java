package com.yujl.coder.core.service;

import com.yujl.coder.core.pojo.dto.FolderDTO;
import com.yujl.coder.core.pojo.entity.GenFolder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yujl.coder.core.pojo.vo.FolderVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
public interface GenFolderService extends IService<GenFolder> {
    FolderDTO getTreeFolder(Long tempId);

    List<Map<String,String>> getFolderPath(Long id);

    void updateById(FolderDTO folderDTO);

    void save(FolderVO folderVO, Long userId);

    void delById(Long id);
}
