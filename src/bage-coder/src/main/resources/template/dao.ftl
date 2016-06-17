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
public interface ${className} extends IDAO<${modelClassName}, ${keyType}> {

}
