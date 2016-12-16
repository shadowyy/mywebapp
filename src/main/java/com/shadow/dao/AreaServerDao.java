package com.shadow.dao;

import com.shadow.domain.AreaPO;
import com.shadow.domain.TitlePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangliang on 16/7/4.
 */
@Repository
public interface AreaServerDao {

    List<AreaPO> queryAreaServerByGameId(String gameId);

    List<TitlePO> getInfo(@Param("gameId") String gameId);
}
