package hu.userrendszerhaz.listener;

import hu.userrendszerhaz.dao.HibernateUtil;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

public class ZKContextListener implements WebAppInit,WebAppCleanup {
    @Override
    public void init(WebApp webApp) throws Exception {

    }

    @Override
    public void cleanup(WebApp webApp) throws Exception {
//        HibernateUtil.getSessionFactory().close();
    }
}
