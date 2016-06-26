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
@Service
public class ${className} extends BaseWebService<${modelClassName}, ${modelClassName}VO, ${modelClassName}AdminVO, ${keyType}> implements ${modelClassName}WebService {
   
    @Autowired
	private ${modelClassName}Service ${serviceObjectName};
	
	@Override
	public IService<${modelClassName}, ${modelClassName}VO, ${modelClassName}AdminVO, ${keyType}> getService() {
		return ${serviceObjectName};
	}
}

