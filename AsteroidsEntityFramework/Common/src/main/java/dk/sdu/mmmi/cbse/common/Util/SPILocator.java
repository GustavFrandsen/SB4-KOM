package dk.sdu.mmmi.cbse.common.Util;

import java.util.*;

public class SPILocator {
    //this class it not used anymore since we needed to swap to java own serviceloader later on.

    @SuppressWarnings("rawtypes")
    private static final Map<Class, ServiceLoader> loadermap = new HashMap<Class, ServiceLoader>();

    private SPILocator() {
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = loadermap.get(service);

        boolean printStatement = false;

        if (loader == null) {
            loader = ServiceLoader.load(service);
            loadermap.put(service, loader);
            printStatement = true;
        }

        List<T> list = new ArrayList<T>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

        if (printStatement) {
            System.out.println("Found " + list.size() + " implementations for interface: " + service.getName());
        }

        return list;
    }
}
