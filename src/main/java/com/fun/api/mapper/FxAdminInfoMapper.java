package com.fun.api.mapper;

import com.fun.api.domain.FxAdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FxAdminInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer fxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    int insert(FxAdminInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    int insertSelective(FxAdminInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    FxAdminInfo selectByPrimaryKey(Integer fxId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FxAdminInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_admin_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FxAdminInfo record);


    FxAdminInfo selectByAccount(@Param("account")String account);
}