package com.yuki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    // 资源名称
    @NotBlank(message = "请输入漫画名称")
    private String fileName;
    // 上传的资源
    @NotNull(message = "请上传文件")
    private MultipartFile file;
    // Tag
    private String tags;
    // 作者
    private String author;
    // 是否为成人内容
    @NotBlank(message = "请选择年龄分级")
    private Integer isadult;
}
