package entry;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;

import java.util.*;

public class CustomStylePlugin extends PluginAdapter {

    @Override
    public void setContext(Context context) {
        // 关闭Javadoc注释
        Properties properties = new Properties();
        properties.put("suppressAllComments", "true");
        context.getCommentGenerator().addConfigurationProperties(properties);
        if (context.getJavaTypeResolverConfiguration() == null) {
            JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
            javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
            context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        }
        super.setContext(context);
    }

    /**
     * 添加lombok注解,实现Serializable接口
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));

        Field versionId = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
        versionId.setFinal(true);
        versionId.setStatic(true);
        versionId.setVisibility(JavaVisibility.PRIVATE);
        versionId.setInitializationString("1L");

        List<Field> fields = new ArrayList<>();
        fields.add(versionId);
        fields.addAll(topLevelClass.getFields());
        topLevelClass.getFields().clear();
        for (Field field : fields) {
            if ("java.lang.Byte".equals(field.getType().getFullyQualifiedName())) {
                field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
            }
            topLevelClass.getFields().add(field);
        }
        return true;
    }

    /** 关闭get方法 **/
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /** 关闭set方法 **/
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 去除insert, updateByPrimaryKey方法,只保留insertSelective等
     */
    private static final Collection<String> ignoreMethods = Arrays.asList();
//    private static final Collection<String> ignoreMethods = Arrays.asList("insert", "updateByPrimaryKey");

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        Map<String, Method> methodMap = new HashMap<>(interfaze.getMethods().size());
        for (Method method : interfaze.getMethods()) {
            if (!ignoreMethods.contains(method.getName())) {
                methodMap.put(method.getName(), method);
            }
        }
        interfaze.getMethods().clear();
        interfaze.getMethods().addAll(methodMap.values());
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 去除重复生成SQL问题
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            java.lang.reflect.Field isMergeableField = sqlMap.getClass().getDeclaredField("isMergeable");
            isMergeableField.setAccessible(true);
            isMergeableField.set(sqlMap, false);
            java.lang.reflect.Field documentField = sqlMap.getClass().getDeclaredField("document");
            documentField.setAccessible(true);
            Document document = (Document) documentField.get(sqlMap);
            XmlElement rootElement = document.getRootElement();
            Map<String, XmlElement> elementMap = new HashMap<>(rootElement.getElements().size());
            for (Element element : rootElement.getElements()) {
                XmlElement xmlElement = (XmlElement) element;
                List<Attribute> attributes = xmlElement.getAttributes();
                for (Attribute attribute : attributes) {
                    if ("id".equalsIgnoreCase(attribute.getName())) {
                        if (!ignoreMethods.contains(attribute.getValue())) {
                            elementMap.put(attribute.getValue(), xmlElement);
                        }
                        break;
                    }
                }
            }
            rootElement.getElements().clear();
            rootElement.getElements().addAll(elementMap.values());
            rootElement.getElements().add(StringElement.build(""));
            rootElement.getElements().add(StringElement.build("<!-- Customer SQL Area -->"));
            rootElement.getElements().add(StringElement.build("\n\n"));
            rootElement.getElements().add(StringElement.build("<!-- Customer SQL Area -->"));
            rootElement.getElements().add(StringElement.build(""));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }

    public static class StringElement extends Element {

        private String content;

        @Override
        public String getFormattedContent(int indentLevel) {
            return content;
        }

        public static StringElement build(String content) {
            StringElement element = new StringElement();
            element.content = content;
            return element;
        }
    }
}