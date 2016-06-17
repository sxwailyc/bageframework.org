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
public class ${className} {

<#list fields as field>
<#list field.annotations as annotation>
    ${annotation}
</#list>
    private ${field.type} ${field.attribute};
    
</#list>
<#list fields as field>
  
    public ${field.type} ${field.getMethod}() {
        return ${field.attribute};
    }

    public void ${field.setMethod}(${field.type} ${field.attribute}) {
        this.${field.attribute} = ${field.attribute};
    }
</#list>

    public static ${className} create(${modelClassName} bean) {
		${className} vo = new ${className}();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}