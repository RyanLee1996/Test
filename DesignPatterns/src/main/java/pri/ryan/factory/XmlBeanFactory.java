package pri.ryan.factory;

import pri.ryan.util.ProxyXml;

public class XmlBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String name) {
        String classPath = ProxyXml.readClass(name);
        try {
            Class clzz = Class.forName(classPath);
            return clzz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
