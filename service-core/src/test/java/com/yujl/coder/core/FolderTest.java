package com.yujl.coder.core;

import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.yujl.coder.core.service.GenCustempService;
import com.yujl.coder.core.service.GenFolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FolderTest {
    @Resource
    private GenFolderService genFolderService;

    @Resource
    private GenCustempService genCustempService;

    @Test
    public void test1() {
        System.out.println(genFolderService.getTreeFolder(1L));
    }

    @Test
    public void test2() {
        System.out.println(genFolderService.getFolderPath(6L));
    }

    @Test
    public void test3(){
        genFolderService.delById(2L);
    }

    @Test
    public void test4(){
        GenCustemp genCustemp = new GenCustemp();
        genCustemp.setContent("2");
        genCustemp.setFileName("das");
        genCustempService.saveOne(genCustemp);
    }
}
