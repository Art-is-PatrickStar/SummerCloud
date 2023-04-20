package com.wsw.summercloud.archive.service.impl;

import cn.hutool.core.date.DateUtil;
import com.wsw.summercloud.api.dto.ResourceInfoQueryDto;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.ResourceInfoResponseDto;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import com.wsw.summercloud.archive.repository.ResourceInfoRepository;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 20:04
 */
@Slf4j
@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {
    @Resource
    private ResourceInfoRepository resourceInfoRepository;

    @Override
    public void saveOrUpdateResourceInfos(List<ResourceInfoRequestDto> requestDtos) {
        List<ResourceInfoEntity> resourceInfoEntities = IResourceInfoConverter.INSTANCE.requestDtoToEntity(requestDtos);
        resourceInfoRepository.saveAll(resourceInfoEntities);
    }

    @Override
    public void updateResourceInfoArchiveStatus(List<Long> resourceIds) {
        resourceInfoRepository.updateResourceInfoArchiveStatus(resourceIds);
    }

    @Override
    public Page<ResourceInfoResponseDto> selectResourceInfos(ResourceInfoQueryDto queryDto) {
        //多条件
        Specification<ResourceInfoEntity> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (!Objects.isNull(queryDto.getResourceId())) {
                //1.获取属性
                Path<Object> resourceId = root.get("resourceId");
                //2.构造查询
                Predicate p1 = criteriaBuilder.equal(resourceId, queryDto.getResourceId());
                //3.将多个查询条件组合到一起(and/or)
                predicate.getExpressions().add(p1);
            } else if (!Objects.isNull(queryDto.getEnableType())) {
                Path<Object> enableType = root.get("enableType");
                Predicate p2 = criteriaBuilder.equal(enableType, queryDto.getEnableType());
                predicate.getExpressions().add(p2);
            } else if (!Objects.isNull(queryDto.getArchiveStatus())) {
                Path<Object> archiveStatus = root.get("archiveStatus");
                Predicate p3 = criteriaBuilder.equal(archiveStatus, queryDto.getArchiveStatus());
                predicate.getExpressions().add(p3);
            } else if (!Objects.isNull(queryDto.getCreatedTimeBegin())) {
                Expression<Date> createdTime = root.get("createdTime").as(Date.class);
                Predicate p4 = criteriaBuilder.greaterThanOrEqualTo(createdTime, queryDto.getCreatedTimeBegin());
                predicate.getExpressions().add(p4);
            } else if (!Objects.isNull(queryDto.getCreatedTimeEnd())) {
                Expression<Date> createdTime = root.get("createdTime").as(Date.class);
                Predicate p5 = criteriaBuilder.lessThanOrEqualTo(createdTime, queryDto.getCreatedTimeEnd());
                predicate.getExpressions().add(p5);
            } else if (!Objects.isNull(queryDto.getUpdatedTimeBegin())) {
                Expression<Date> updatedTime = root.get("updatedTime").as(Date.class);
                Predicate p6 = criteriaBuilder.greaterThanOrEqualTo(updatedTime, queryDto.getUpdatedTimeBegin());
                predicate.getExpressions().add(p6);
            } else if (!Objects.isNull(queryDto.getUpdatedTimeEnd())) {
                Expression<Date> updatedTime = root.get("updatedTime").as(Date.class);
                Predicate p7 = criteriaBuilder.lessThanOrEqualTo(updatedTime, queryDto.getUpdatedTimeEnd());
                predicate.getExpressions().add(p7);
            }
            return predicate;
        };
        //排序
        Sort sort = Sort.by(Sort.Order.desc("updatedTime"));
        //分页
        PageRequest pageRequest = PageRequest.of(queryDto.getCurrentPage(), queryDto.getPageSize(), sort);
        //查询
        //page中 content为查询结果 totalElements为总条数 totalPages为总页数 number为当前页码 size为每页条数
        Page<ResourceInfoEntity> resourceInfoEntities = resourceInfoRepository.findAll(specification, pageRequest);
        return resourceInfoEntities.map(IResourceInfoConverter.INSTANCE::entityToResponseDto);
    }

    @Override
    public List<ResourceInfoEntity> getNotArchivedResources() {
        Date createdTime = DateUtil.beginOfHour(new Date());
        return resourceInfoRepository.getNotArchivedResources(createdTime);
    }
}
