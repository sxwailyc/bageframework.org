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
@Controller
@RequestMapping(value = ${className}.DIR)
public class ${className} {

	public static final String DIR = "${pathPrefix}";

	public static Logger logger = Logger.getLogger(${className}.class);

	@RequestMapping
	public ModelAndView list(HttpServletRequest req) {

		ModelAndView model = new ModelAndView("${templatePath}/list");
		return model;

	}

}
