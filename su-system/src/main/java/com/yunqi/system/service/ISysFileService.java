package com.yunqi.system.service;

import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Files;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.common.utils.DateUtil;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysFile;
import org.nutz.dao.Cnd;
import org.nutz.lang.random.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Created by @author CHQ on 2022/12/18
 */
@Service
public class ISysFileService  extends BaseServiceImpl<SysFile> {

    /**
     * 文件列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<SysFile> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:文件名称
        if(Strings.isNotBlank(query.getString("fileName"))){
            cnd.and("fileName", "like", "%" + query.getString("fileName") + "%");
        }
        // 时间范围:根据时间戳范围查询
        if(Strings.isNotBlank(query.getString("beginTime")) || Strings.isNotBlank(query.getString("endTime"))){
            cnd.and("createdAt",">=", query.getString("beginTime"));
            cnd.and("createdAt","<=", query.getString("endTime"));
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(query.page(),query.pageSize(), cnd);
    }



    @Transactional
    public String create(MultipartFile file, String path){
        if (file == null) {
            return "";
        }
        // 文件名称
        String fileName = file.getOriginalFilename();
        String fileMd5 = R.UU32();

        // 获取后缀名
        String fileSuffix = Objects.requireNonNull(fileName).substring(fileName.indexOf(".")+ 1);
        // 日期路径
        String dateStr = DateUtil.format(DateUtil.date(), "yyyyMMdd");

        // 拼接创建文件地址
        String f = path +  "/" + dateStr +  "/" + fileMd5 + '.' + fileSuffix;
        String filePath = GlobalConstant.DEFAULT_SYSTEM_UPLOAD_PATH  + f;

        try {
            // 写入文件
            Files.write(new File( GlobalConstant.DEFAULT_SYSTEM_ROOT + filePath), file.getInputStream());
            // 保存文件
            SysFile sysFile = new SysFile()
                    .setFileType(file.getContentType())
                    .setFileMd5(fileMd5)
                    .setFileName(fileName)
                    .setFileSizeKb(getFileOrFilesSize(file.getSize(), SIZETYPE_KB) + "KB")
                    .setFileSuffix(fileSuffix)
                    .setFilePath(filePath);
            this.insert(sysFile);
            return filePath;
        } catch (Exception e){
            logger.error("IO Upload Files FAIL");
            return "";
        }
    }


    /**
     * 删除文件
     * @param id 文件ID
     */
    @Transactional
    public int deleteById(String id){
        SysFile sysFile = this.fetch(id);
        if(null != sysFile){
            File file = new File(GlobalConstant.DEFAULT_SYSTEM_ROOT + sysFile.getFilePath());
            // 删除文件
            if(file.exists() && file.isFile()){
                Files.deleteFile(file);
            }
        }
        return this.delete(id);
    }

    /**
     * 批量删除文件
     * @param ids 文件ID数组
     */
    @Transactional
    public void deleteByIds(String ids) {
        if(Strings.isNotBlank(ids)){
            String[] arr =  ids.split(",");
            for (String id: arr) {
                this.deleteById(id);
            }
        }
    }

    /**
     * 下载文件
     * @param id    文件ID
     * @return      文件
     */
    @Transactional
    public File download(String id) {
        SysFile sysFile = this.fetch(id);
        if(null != sysFile){
            File file = new File(GlobalConstant.DEFAULT_SYSTEM_ROOT + sysFile.getFilePath());
            if(file.exists() && file.isFile()){
                return file;
            }else{
                throw new BizException("文件资源不存在");
            }
        }else{
            throw new BizException("文件数据不存在");
        }
    }

    /**
     * 上传图片
     * @param file  图片文件
     * @return      文件路径
     */
    public String upLoadImage(MultipartFile file) {
        return this.create(file,"/image");
    }

    /**
     * 上传文件
     * @param file  文件
     * @return      文件路径
     */
    public String upLoadFile(MultipartFile file) {
        return this.create(file,"/file");
    }



    public static final int SIZETYPE_B = 1; //获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2; //获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3; //获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4; //获取文件大小单位为GB的double值


    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileSize   文件大小
     * @param sizeType   转换类型
     * @return           文件大小
     */
    private double getFileOrFilesSize(long fileSize, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.parseDouble(df.format((double) fileSize));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.parseDouble(df.format((double) fileSize / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.parseDouble(df.format((double) fileSize / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.parseDouble(df.format((double) fileSize / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

}
