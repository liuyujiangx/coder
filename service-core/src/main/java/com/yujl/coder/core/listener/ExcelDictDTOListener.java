package com.yujl.coder.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yujl.coder.core.mapper.DictMapper;
import com.yujl.coder.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 19145
 */
@NoArgsConstructor //无参
@Slf4j
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {
    private static final int BATCH_COUNT = 5;
    private int count = 0;
    List<ExcelDictDTO> list = new ArrayList<>();
    private DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    /**
     * 遍历每一行记录
     */
    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：" + excelDictDTO);
        count++;
        list.add(excelDictDTO);
        if (list.size() > BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("解析完成,共{}条数据", count);
    }

    /**
     * 存储数据库
     */
    private void saveData() {
        if (list.size() > 0) {
            log.info("{}条数据，开始存储数据库！", list.size());
            //批量插入
            dictMapper.insertBatch(list);
            log.info("存储数据库成功！");
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
