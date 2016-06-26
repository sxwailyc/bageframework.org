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
public interface ${className}{

    public Page<${modelClassName}AdminVO> page(Query query, int pageNo, int pageSize);

	public ${modelClassName} get(${keyType} id);

	public boolean update(${modelClassName} bean);

	public boolean add(${modelClassName} bean);

	public boolean delete(${keyType} id);
}
