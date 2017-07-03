package com.lanlinker.service;

import com.lanlinker.domain.*;
import com.lanlinker.properties.FileLocation;
import com.lanlinker.repository.AttachmentRepository;
import com.lanlinker.repository.PictureRepository;
import com.lanlinker.repository.ResumeRepository;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 简历类业务层操作对象
 */
@Service
public class ResumeService {
    /**
     * 注入简历类持久层操作对象
     */
    @Autowired
    private ResumeRepository resumeRepository;
    /**
     * 注入图片类持久层操作对象
     */
    @Autowired
    private PictureRepository pictureRepository;
    /**
     * 注入附件类持久层操作对象
     */
    @Autowired
    private AttachmentRepository attachmentRepository;
    /**
     * 注入文件配置类对象
     */
    @Autowired
    private FileLocation fileLocation;
    /**
     * 日期工具类
     */
    private Calendar calendar = Calendar.getInstance();
    /**
     * 添加简历
     * @param resume
     */
    public void addResume(Resume resume){
        /**
         * 补全简历信息
         */
        resume.setStatus(0);
        resume.setCreateDate(new Date());
        /**
         * 插入简历
         */
        resumeRepository.save(resume);
    }

    /**
     * 查询简历
     * @param pageIndex 首页索引
     * @param pageSize  每页数量
     * @param sortName  排序名称
     * @param sortOrder 排序方式
     * @param resume    简历对象
     * @return          查询结果
     */
    public ResumeVO findAllResumes(int pageIndex, int pageSize, String sortName, String sortOrder,final Resume resume){
        // 计算当前页
        int pageNumber = pageIndex/pageSize;
        // 创建分页和添加排序条件
        Pageable pageAble = null;
        // 创建排序
        if(sortName!=null&&sortOrder!=null){
            Sort.Order order = new Sort.Order(sortOrder.equals("desc")?Sort.Direction.DESC: Sort.Direction.ASC,sortName);
            Sort sort = new Sort(order);
            pageAble = new PageRequest(pageNumber,pageSize,sort);
        }else{
            pageAble = new PageRequest(pageNumber,pageSize);
        }
        // 创建查询条件
        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 状态查询
                Path<Object> status = root.get("status");
                // 判断是否是查询默认状态(0为默认状态,表示查询1和2状态)
                Predicate statusPre = null;
                if(resume.getStatus() == 0){
                    statusPre = criteriaBuilder.between(status.as(Integer.class),0,2);
                }else {
                    statusPre = criteriaBuilder.equal(status, resume.getStatus());
                }
                // 姓名查询
                Path<Object> name = root.get("name");
                Predicate namePre = criteriaBuilder.like(name.as(String.class), "%"+resume.getName()+"%");
                // 身份证号查询
                Path<Object> card = root.get("card");
                Predicate cardPre = criteriaBuilder.like(card.as(String.class), "%"+resume.getCard()+"%");
                // 学历查询
                Path<Object> education = root.get("education");
                Predicate educationPre = null;
                if(resume.getEducation()==null){
                    educationPre = criteriaBuilder.like(education.as(String.class), "%%");
                }else {
                    educationPre = criteriaBuilder.like(education.as(String.class), "%"+resume.getEducation()+"%");
                }
                // 应聘职位查询
                Path<Object> job = root.get("job");
                Predicate jobPre = null;
                if(resume.getJob()==null){
                    jobPre = criteriaBuilder.like(job.as(String.class), "%%");
                }else {
                    jobPre = criteriaBuilder.like(job.as(String.class), "%"+resume.getJob()+"%");
                }
                Predicate predicate = criteriaBuilder.and(statusPre,namePre,cardPre,educationPre,jobPre);

                // 日期查询
                if(resume.getCreateDate()!=null){
                    Path<Object> createDate = root.get("createDate");
                    calendar.setTime(resume.getCreateDate());
                    calendar.add(Calendar.DATE,1);
                    predicate = criteriaBuilder.and(statusPre,namePre,cardPre,educationPre,jobPre,
                            criteriaBuilder.greaterThanOrEqualTo(createDate.as(Date.class),resume.getCreateDate()),
                            criteriaBuilder.lessThan(createDate.as(Date.class),calendar.getTime()));
                }

                return predicate;
            }
        };

        // 查询分页
        Page<Resume> page = resumeRepository.findAll(specification,pageAble);
        // 创建返回对象
        ResumeVO resumeVO = new ResumeVO();
        resumeVO.setTotal(page.getTotalElements());
        resumeVO.setRows(page.getContent());
        return resumeVO;
    }

    /**
     * 根据Id删除简历
     * @param id 简历ID
     */
    public void deleteResumeById(Integer id){
        resumeRepository.delete(id);
    }

    /**
     * 上传笔试图片(添加事务支持,因为存在两个修改表的SQL)
     * @param imgs
     * @param id
     * @return
     */
    @Transactional
    public Msg uploadImg(MultipartFile[] imgs, Integer id){
        // 创建返回结果对象
        Msg result = new Msg();
        // 获取id
        if (id==null){
            result.setMsg("请先选择简历后再上传图片");
            return result;
        }
        // 创建文件的prefix
        String prefix = "";
        // 获取当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取三位随机数
        Random random = new Random();
        // 获取文件的suffix
        String oldFileName = null;
        String suffix = null;
        // 组合文件的名字
        String fileName = null;
        // 获取虚拟服务路径
        String path = null;
        // 判断图片是否为空
        if(imgs!=null){
            for(MultipartFile img:imgs){
                prefix += dateFormat.format(new Date());
                for (int i = 0; i < 3; i++) {
                    prefix += random.nextInt(10);
                }
                // 获取文件的suffix
                oldFileName = img.getOriginalFilename();
                suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
                // 组合文件的名字
                fileName = prefix + suffix;
                // 获取虚拟服务路径
                path = fileLocation.getTotalPath() + fileLocation.getImgPath() + fileName;
                // 输出
                System.out.println("文件存储路径:"+path);
                try {
                    // 保存文件
                    img.transferTo(new File(path));
                    // 保存图片名称到数据库
                    Picture picture = new Picture();
                    picture.setUid(id);
                    picture.setName(fileName);
                    pictureRepository.save(picture);
                    // 设置返回结果
                    result.setMsg("上传图片成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    result.setMsg("上传图片失败");
                    return result;
                }
            }
        }
        // 修改该简历的状态位为1,表示该简历已经上传过了笔试图片
        Resume resume = resumeRepository.findOne(id);
        // 如果当前简历状态为简历已上传,则修改状态为3,表示简历和笔试都已经上传成功
        if(resume.getStatus()==2){
            resume.setStatus(3);
        }else{
            resume.setStatus(1);
        }
        resumeRepository.save(resume);

        return result;
    }
    /**
     * 上传简历(添加事务支持,因为存在两个修改表的SQL)
     * @param attachment
     * @param id
     * @return
     */
    @Transactional
    public Msg uploadAttachment(MultipartFile attachment, Integer id){
        // 创建返回结果对象
        Msg result = new Msg();
        // 获取id
        if (id==null){
            result.setMsg("请先选择简历后再上传");
            return result;
        }
        // 判断图片是否为空
        if(attachment==null){
            result.setMsg("简历附件不允许为空");
            return result;
        }
        // 获取文件的suffix
        String oldFileName = attachment.getOriginalFilename();
        String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
        // 获取简历
        Resume resume = resumeRepository.findOne(id);
        // 组合文件的名字
        String fileName = resume.getJob() + resume.getName() + suffix;
        // 获取虚拟服务路径
        String path = fileLocation.getTotalPath() + fileLocation.getFilePath() + fileName;
        // 输出
        System.out.println("文件存储路径:"+path);
        try {
            // 保存文件
            attachment.transferTo(new File(path));
            // 保存图片名称到数据库
            Attachment attach = new Attachment();
            attach.setUid(id);
            attach.setName(fileName);
            attachmentRepository.save(attach);
            // 设置返回结果
            result.setMsg("上传简历成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("上传简历失败");
            return result;
        }
        // 如果当前图片状态为图片已上传,则修改状态为3,表示简历和笔试都已经上传成功
        if(resume.getStatus()==1){
            resume.setStatus(3);
        }else{
            resume.setStatus(2);
        }
        resumeRepository.save(resume);

        return result;
    }
    /**
     * 根据职位和状态查询简历列表
     * @param job    职位编号
     * @param status 状态编号
     * @return
     */
    public List<Resume> findResumeByJobAndStatus(Integer job,Integer status){
        return resumeRepository.findAllByJobAndStatus(job,status);
    }
    /**
     * 根据状态位统计简历数量
     * @param status
     * @return
     */
    public Integer countResumesByStatus(Integer status){
        return resumeRepository.countAllByStatus(status);
    }
    /**
     * 根据状态位查询简历列表
     * @param status
     * @return
     */
    public List<Resume> findResumesByStatus(Integer status){
        return resumeRepository.findAllByStatus(status);
    }
    /**
     * 查询所有简历数量
     * @return 所有简历的数量
     */
    public Integer countAllResumes(){
        return resumeRepository.countAllBy();
    }

    /**
     * 查询当天新增简历数量
     * @return
     * @throws ParseException
     */
    public Integer countTodayResumes() throws ParseException {
        Date start = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(start);
        Date parseDate = format.parse(str);
        calendar.setTime(parseDate);
        calendar.add(Calendar.DATE,1);
        return resumeRepository.countAllByCreateDateIsGreaterThanEqualAndCreateDateLessThan(parseDate,calendar.getTime());
    }
}
