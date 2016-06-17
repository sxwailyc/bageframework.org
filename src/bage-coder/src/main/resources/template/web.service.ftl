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
public interface ${className} extends IService<${modelClassName}, ${modelClassName}VO, ${modelClassName}AdminVO, ${keyType}> {

}
