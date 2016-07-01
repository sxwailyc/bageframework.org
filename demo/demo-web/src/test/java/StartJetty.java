import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import com.bageframework.jetty.EmbedAnnotionConfiguration;
import com.bageframework.jetty.EmbedMetaInfConfiguration;
import com.bageframework.jetty.EmbedWebInfConfiguration;
import com.bageframework.jetty.EmbedWebXmlConfiguration;

public class StartJetty {

	private static Logger logger = Logger.getLogger(StartJetty.class);

	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(9000, "/");
		server.start();
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {

		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		//webContext.setDefaultsDescriptor("leopard-test/webdefault.xml");

		// 问题点：http://stackoverflow.com/questions/13222071/spring-3-1-webapplicationinitializer-embedded-jetty-8-annotationconfiguration
		webContext.setConfigurations(new Configuration[] { //
				new EmbedWebInfConfiguration()//
						, new EmbedWebXmlConfiguration()//
						, new EmbedMetaInfConfiguration()//
						, new FragmentConfiguration()//
						, new EmbedAnnotionConfiguration() //
				// new PlusConfiguration(),
				// new EnvConfiguration()
				});

		WebAppClassLoader classLoader = null;
		try {
			// addTldLib(webContext);
			classLoader = new WebAppClassLoader(webContext);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ClassLoader tldClassLoader = addTldLib(classLoader);
		webContext.setClassLoader(classLoader);

		webContext.setParentLoaderPriority(true);
		logger.debug(webContext.dump());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);

		return server;
	}
}
