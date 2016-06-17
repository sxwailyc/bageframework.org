package ${packageName};

<#list imports as import>
import ${import};
</#list>

/**
 * 
 * 
 * @author ${author}
 * 
 */
@Repository
public class ${className} extends BaseMysqlDao<${modelClassName}> implements ${modelClassName}Dao {

}
