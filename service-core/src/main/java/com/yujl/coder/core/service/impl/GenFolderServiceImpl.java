package com.yujl.coder.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yujl.coder.core.pojo.dto.FolderDTO;
import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.yujl.coder.core.pojo.entity.GenFolder;
import com.yujl.coder.core.mapper.GenFolderMapper;
import com.yujl.coder.core.pojo.vo.FolderVO;
import com.yujl.coder.core.service.GenCustempService;
import com.yujl.coder.core.service.GenFolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
@Service
public class GenFolderServiceImpl extends ServiceImpl<GenFolderMapper, GenFolder> implements GenFolderService {

    @Resource
    private GenCustempService genCustempService;

    @Override
    public FolderDTO getTreeFolder(Long tempId) {
        QueryWrapper<GenFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);
        queryWrapper.eq("temp_id", tempId);
        //查找第一层
        GenFolder genFolder = baseMapper.selectOne(queryWrapper);
        if (genFolder == null) {
            return null;
        }
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setId(genFolder.getId());
        folderDTO.setPid(genFolder.getPid());
        folderDTO.setFile(genFolder.getFile());
        folderDTO.setLabel(genFolder.getName());
        folderDTO.setChildren(getTree(genFolder));
        return folderDTO;
    }

    @Override
    public List<Map<String, String>> getFolderPath(Long id) {
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> folderPath = new LinkedList<>();
        GenFolder genFolder = baseMapper.selectById(id);
        while (genFolder.getPid() > 0L) {
            map = new HashMap<>();
            map.put("id", genFolder.getId().toString());
            map.put("label", genFolder.getName());
            folderPath.add(0, map);
            genFolder = baseMapper.selectById(genFolder.getPid());
        }
        map = new HashMap<>();
        map.put("id", genFolder.getId().toString());
        map.put("label", genFolder.getName());
        folderPath.add(0, map);
        return folderPath;
    }

    @Override
    public void updateById(FolderDTO folderDTO) {
        GenFolder genFolder = baseMapper.selectById(folderDTO.getId());
        genFolder.setName(folderDTO.getLabel());
        QueryWrapper<GenFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", folderDTO.getId());
        baseMapper.update(genFolder, queryWrapper);
    }

    @Override
    public void save(FolderVO folderVO, Long userId) {
        GenFolder genFolder = vo2Entity(folderVO);
        genFolder.setUserId(userId);
        baseMapper.insert(genFolder);
    }

    @Override
    public void delById(Long id) {
        if (id == 0L) {
            return;
        }
        baseMapper.deleteById(id);
        QueryWrapper<GenCustemp> delWrapper = new QueryWrapper<>();
        delWrapper.eq("file_id",id);
        genCustempService.remove(delWrapper);
        QueryWrapper<GenFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        List<GenFolder> children = baseMapper.selectList(queryWrapper);
        for (GenFolder genFolder : children) {
            delById(genFolder.getId());
        }
    }

    public List<FolderDTO> getTree(GenFolder genFolder) {

        QueryWrapper<GenFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", genFolder.getId());
        queryWrapper.eq("temp_id", genFolder.getTempId());
        List<GenFolder> folders = baseMapper.selectList(queryWrapper);
        List<FolderDTO> folderDTOList = toDTO(folders);
        if (folders.size() == 0) {
            return null;
        }
        for (int i = 0; i < folderDTOList.size(); i++) {
            folderDTOList.get(i).setChildren(getTree(folders.get(i)));
        }
        return folderDTOList;
    }

    public List<FolderDTO> toDTO(List<GenFolder> genFolders) {
        List<FolderDTO> folderDtoS = new ArrayList<>();
        for (GenFolder genFolder : genFolders) {
            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setId(genFolder.getId());
            folderDTO.setFile(genFolder.getFile());
            folderDTO.setPid(genFolder.getPid());
            folderDTO.setLabel(genFolder.getName());
            folderDtoS.add(folderDTO);
        }
        return folderDtoS;
    }

    public GenFolder vo2Entity(FolderVO folderVO) {
        GenFolder genFolder = new GenFolder();
        genFolder.setPid(folderVO.getPid());
        genFolder.setName(folderVO.getLabel());
        genFolder.setFile(folderVO.getFile());
        genFolder.setTempId(folderVO.getTempId());
        genFolder.setDeleted(false);
        genFolder.setCreateTime(LocalDateTime.now());
        return genFolder;
    }
}
