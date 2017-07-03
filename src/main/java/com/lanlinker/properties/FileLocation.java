package com.lanlinker.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件存储路径配置
 */
@Component
@ConfigurationProperties(prefix = "fileLocation")
public class FileLocation {
    // 上层路径
    private String totalPath;
    // 图片路径
    private String imgPath;
    // 文件路径
    private String filePath;

    public String getTotalPath() {
        return totalPath;
    }

    public void setTotalPath(String totalPath) {
        this.totalPath = totalPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
