package com.lanlinker.repository;

import com.lanlinker.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 图片类持久层操作
 */
public interface PictureRepository extends JpaRepository<Picture,Integer>{
    // 根据ID查找图片
    List<Picture> findAllByUid(Integer uid);
}
