package com.connext.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/*      (1)、@Entity 代表此类映射为数据库的表结构
        (2)、@Table(name="tbl_dept")此注解用于配置实体类与表映射的关系,name代表映射的表名
        (3)、 @Id注解代表此类为一个主键
        (4)、@GeneratedValue注解用于配置主键相关信息,generator属性用于配置生成策略有以下几种枚举值:
        　　1、auto - 主键由程序控制 。
        　　2、IDENTITY - 由数据库自动生成。
        　　3、enerator -指定生成主键使用的生成器 。
        　4、SEQUENCE - 根据底层数据库的序列来生成主键 。
        　　5、TABLE - 使用一个特定的数据库表来保存主键。
        　　6、system-uuid 代表使用系统生成的uuid进行配。
        (5)、@Column用于配置列相关信息的注解
　　        1、name字段用于指定映射到表结构的映射字段。
        　　2、length代表此字段的长度约束,可以省略。
        　　3、unique属性代表此字段是否开启唯一性约束，默认为false,唯一则为true 。
        　　4、nullable代表此字段是否可以为空,默认为true 。 false代表不能为空 。
        （6）、@DateTimeFormat用于映射数据库表时间的格式。
        相应的get和set方法已经省略。*/
@ApiModel(description = "平台")
@Entity(name = "Platform")
@Table(name = "platform")
public class Platform {

    @Id //此备注代表该字段为该类的主键
    @Column(name = "Id")
    //name - 指定对应列的名称 ,length - 最大长度
    //nullable - 是否可以为null,默认为true   unique - 是否唯一,默认为false
    private String id;

    @Column(name = "Code")
    private String code;

    @Column(name = "`Name`")
    private String name;

    @Column(name = "useforbudget")
    private Boolean useForBudget;

    @Column(name = "`order`")
    private Integer order;

    @Column(name = "bewrite")
    private String bewrite;

    @Column(name = "createuser")
    private String createUser;

    @Column(name = "modifyuser")
    private String modifyUser;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "modifytime")
    private Date modifyTime;

    @Column(name = "isdeleted")
    private Boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUseForBudget() {
        return useForBudget;
    }

    public void setUseForBudget(Boolean useForBudget) {
        this.useForBudget = useForBudget;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getBewrite() {
        return bewrite;
    }

    public void setBewrite(String bewrite) {
        this.bewrite = bewrite;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

/*注意事项：
1.name、order等关键词需要加``
2.时间目前用的是Date类型
3.@Column不能填写createTime这样驼峰命名，会转化成create_time
    解决方法：
    1.改为全部小写createtime
    2.在application-dev.properties中添加增加
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl*/
}
