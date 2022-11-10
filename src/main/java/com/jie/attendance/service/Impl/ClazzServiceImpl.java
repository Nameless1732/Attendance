package com.jie.attendance.service.Impl;

import com.jie.attendance.entity.Clazz;
import com.jie.attendance.mapper.ClazzMapper;
import com.jie.attendance.service.ClazzService;
import com.jie.attendance.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public PageBean<Clazz> queryPage(Map<String, Object> paramMap) {
        PageBean<Clazz> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Clazz> datas = clazzMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = clazzMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addClazz(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteClazz(List<Integer> ids) {
        return clazzMapper.deleteBatchIds(ids);
    }

    @Override
    public int editClazz(Clazz clazz) {
        return clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> getClazzById(List<Integer> ids) {
        return clazzMapper.selectBatchIds(ids);
    }
}
