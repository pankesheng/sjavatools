package com.ks.utils.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.zcj.util.coder.database.Database;
import com.zcj.util.coder.database.DatabaseBuilder;
import com.zcj.util.coder.java.JavaCode;
import com.zcj.util.coder.java.JavaCodeBuilder;
import com.zcj.util.coder.page.PageBean;
import com.zcj.util.coder.page.PageBuilder;

/**
 * @author pks
 * @version 2018年1月12日
 */
public class GeneratorUtil {
	
	private static String SAVEPATH ;

	private static String DATABASETYPE ;// MySQL、SqlServer

	private static String ENTITYPACKAGENAME ;
	
	private static FreeMarkerConfigurer freemarkerConfig = null;
	
	static{
		ApplicationContext context = new FileSystemXmlApplicationContext(TestCode.class.getResource("").getPath()+"application.xml");
		if (context != null) {
			freemarkerConfig = (MyFreeMarkerConfigurer) context.getBean("freemarkerConfig");
		}
	}
	
	public static void gen(String entity_package,String save_path,String database_type){
		SAVEPATH = save_path;
		DATABASETYPE = database_type;
		ENTITYPACKAGENAME = entity_package;
		
		Set<Class<?>> classesSet = UtilClass.getClasses(ENTITYPACKAGENAME);
		Class<?>[] test = new Class<?>[classesSet.size()];
		Class<?>[] carray = (Class<?>[]) classesSet.toArray(test);
		for (Class<?> c : carray) {
			System.out.println(c.getName());
		}
		allJavaAndFtl(carray);
		allTable(carray);
	}
	
	public static void main(String[] args) {
		gen("com.ks.entity", "F:/code_temp/", Database.TYPE_MYSQL);
	}
	
	private static void allTable(Class<?>[] carray) {
		Database database = DatabaseBuilder.initDatabase(carray, DATABASETYPE);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tables", database.getTables());
		root.put("databasename", database.getName());
		if (Database.TYPE_MYSQL.equals(DATABASETYPE)) {
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root,TestCode.class.getResource("").getPath()+"DatabaseMySQL.ftl", SAVEPATH + "init.sql");
		} else if (Database.TYPE_SQLSERVER.equals(DATABASETYPE)) {
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"DatabaseSqlServer.ftl", SAVEPATH + "init.sql");
		} else if (Database.TYPE_ORACLE.equals(DATABASETYPE)) {
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"DatabaseOracle.ftl", SAVEPATH + "init.sql");
		}
	}

	private static void allJavaAndFtl(Class<?>[] carray) {
		for (Class<?> c : carray) {
			allJava(c);
			allFtl(c);
		}
	}

	private static void allFtl(Class<?> className) {
		PageBean obj = PageBuilder.initPage(className);
		if (obj != null) {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("obj", obj);
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root,TestCode.class.getResource("").getPath()+"Page_xx_list.ftl",
					SAVEPATH + "/WEB-INF/ftl/admin/" + obj.getModuleName().toLowerCase() + "/" + obj.getClassName().toLowerCase()
							+ "_list.ftl");
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root,TestCode.class.getResource("").getPath()+"Page_xx_modify.ftl",
					SAVEPATH + "/WEB-INF/ftl/admin/" + obj.getModuleName().toLowerCase() + "/" + obj.getClassName().toLowerCase()
							+ "_modify.ftl");
		}
	}

	private static void allJava(Class<?> className) {

		JavaCode code = JavaCodeBuilder.initJavaCode(className);

		Map<String, Object> root = new HashMap<String, Object>();
		if (code != null) {
			root.put("packages", code.getPackageName());
			root.put("modules", code.getModuleName());
			root.put("classes", code.getClassName());
			root.put("tables", code.getTableName());
			root.put("fields", code.getFieldList());
			root.put("qbuilderList", code.getQbuilderList());
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxMapperJava.ftl",
					SAVEPATH + code.getPackageName() + "/mapper/" + code.getModuleName() + "/" + code.getClassName() + "Mapper.java");
			if (Database.TYPE_MYSQL.equals(DATABASETYPE)) {
				FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxMapperXmlMySQL.ftl",
						SAVEPATH + code.getPackageName() + "/mapper/" + code.getModuleName() + "/" + code.getClassName() + "Mapper.xml");
			} else if (Database.TYPE_SQLSERVER.equals(DATABASETYPE)) {
				FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxMapperXmlSqlServer.ftl",
						SAVEPATH + code.getPackageName() + "/mapper/" + code.getModuleName() + "/" + code.getClassName() + "Mapper.xml");
			} else if (Database.TYPE_ORACLE.equals(DATABASETYPE)) {
				FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxMapperXmlOracle.ftl",
						SAVEPATH + code.getPackageName() + "/mapper/" + code.getModuleName() + "/" + code.getClassName() + "Mapper.xml");
			}
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxService.ftl",
					SAVEPATH + code.getPackageName() + "/service/" + code.getModuleName() + "/" + code.getClassName() + "Service.java");
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxServiceImpl.ftl",
					SAVEPATH + code.getPackageName() + "/service/" + code.getModuleName() + "/" + code.getClassName() + "ServiceImpl.java");
			FreemarkerUtil.getInstance(freemarkerConfig).htmlFile(root, TestCode.class.getResource("").getPath()+"XxAction.ftl",
					SAVEPATH + code.getPackageName() + "/action/" + code.getModuleName() + "/" + code.getClassName() + "Action.java");
		} else {
			System.out.println("生成 " + className.getName() + " 失败");
		}

	}
	
}

