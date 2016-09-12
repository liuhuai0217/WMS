package com._520it.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

import com._520it.ssh.domain.Client;
import com._520it.ssh.domain.ProductStock;
import com._520it.ssh.domain.SaleAccount;
import com._520it.ssh.domain.StockIncomeBill;
import com._520it.ssh.domain.StockOutcomeBill;

import freemarker.template.Configuration;
import freemarker.template.Template;

//代码生成器
public class CodeGenerator {

    private static Configuration config;

    static {
        try {
            //1:创建配置对象
            config = new Configuration(Configuration.VERSION_2_3_22);
            //2:设置模板文件加载目录
            config.setDirectoryForTemplateLoading(new File("templates"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //generateCode();
        System.out.println("生成完毕");
    }

    private static void generateCode() throws Exception {
        ClassInfo classInfo = new ClassInfo(SaleAccount.class);
        //1:生成DAO组件
        createFile(classInfo, "IDAO.java", "src/main/java/{0}/dao/" + "I{1}DAO.java");
        createFile(classInfo, "DAOImpl.java", "src/main/java/{0}/dao/impl/" + "{1}DAOImpl.java");
        //2:生成service组件
        createFile(classInfo, "IService.java", "src/main/java/{0}/service/" + "I{1}Service.java");
        createFile(classInfo, "ServiceImpl.java", "src/main/java/{0}/service/impl/" + "{1}ServiceImpl.java");
        //3:生成Query和Action组件
        createFile(classInfo, "QueryObject.java", "src/main/java/{0}/query/" + "{1}QueryObject.java");
        createFile(classInfo, "Action.java", "src/main/java/{0}/web/action/" + "{1}Action.java");
        //4:生成list.jsp和input.jsp以及映射文件
        createFile(classInfo, "list.jsp", "src/main/webapp/WEB-INF/views/{2}/list.jsp");
        createFile(classInfo, "input.jsp", "src/main/webapp/WEB-INF/views/{2}/input.jsp");
        createFile(classInfo, "hbm.xml", "src/main/resources/{0}/domain/{1}.hbm.xml");
        //追加配置文件:applicationContext-XX.xml,不是生成文件,而是在现有文件末尾,插入一个<bean>元素
        appendToXml(classInfo,"dao.xml","src/main/resources/applicationContext-dao.xml");
        appendToXml(classInfo,"service.xml","src/main/resources/applicationContext-service.xml");
        appendToXml(classInfo,"action.xml","src/main/resources/applicationContext-action.xml");
    }

    /**
     * 在XML文件中插入一段内容
     * @param  targetFile 把生成的bean元素插入到的指定的文件中
     */
    private static void appendToXml(ClassInfo classInfo, String templateName, String targetFile) throws Exception {
        Template template = config.getTemplate(templateName);
        StringWriter out = new StringWriter();
        template.process(classInfo, out);//把合并的数据输出到一个String的流中
        String appendXml = out.toString();
        XmlUtil.appendXML(new File(targetFile),appendXml);
    }

    /**
     * 生成文件
     *
     * @param classInfo    封装数据的对象
     * @param templateName 对应的模板名称
     * @param path         生成文件的路径,需要把.换成/
     */
    private static void createFile(ClassInfo classInfo, String templateName, String path) throws Exception {
        Template template = config.getTemplate(templateName);
        //设置{0}和{1}的值
        String filePath = MessageFormat.format(path, classInfo.getBasePkg().replace(".", "/"), classInfo.getClassName(), classInfo.getObjectName());
        File file = new File(filePath);//生成的文件对象
        if(!file.getParentFile().exists()){//判断当前文件的父目录是否存在,如果不存在,则生成
            file.getParentFile().mkdirs();
        }
        template.process(classInfo, new FileWriter(file));
    }
}
