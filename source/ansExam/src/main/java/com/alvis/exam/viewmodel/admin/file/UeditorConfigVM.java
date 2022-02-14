package com.alvis.exam.viewmodel.admin.file;

import lombok.Data;

import java.util.List;

@Data
public class UeditorConfigVM {
    private String imageActionName;
    private  String imageFieldName;
    private Long imageMaxSize;
    private List<String> imageAllowFiles;
    private boolean imageCompressEnable;
    private Integer imageCompressBorder;
    private String imageInsertAlign;
    private String imageUrlPrefix;
    private String imagePathFormat;
    /* 上传视频配置 */
    private String videoActionName;
    private String videoFieldName;
    private String videoPathFormat;
    private String videoUrlPrefix;
    private Long videoMaxSize;
    private List<String> videoAllowFiles;
    /* 上传文件配置 */
    private String fileActionName;
    private String fileFieldName;
    private String filePathFormat;
    private String fileUrlPrefix;
    private Long fileMaxSize;
    private List<String> fileAllowFiles;

}
