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
public class ${className} extends BaseService<${modelClassName}, ${modelClassName}VO, ${modelClassName}AdminVO, ${keyType}> implements ${modelClassName}Service {

	@Autowired
	private ${modelClassName}Dao ${daoObjectName}Dao;

	@Override
	public IDAO<${modelClassName}, String> getDao() {
		return ${daoObjectName}Dao;
	}

	@Override
	public ${modelClassName}VO model2Vo(${modelClassName} bean) {
		return null;
	}

	@Override
	public ${modelClassName}AdminVO model2AdminVo(${modelClassName} bean) {
		return ${modelClassName}AdminVO.create(bean);
	}

}
