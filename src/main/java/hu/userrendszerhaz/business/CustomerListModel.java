package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerListModel extends ListModelList<Customer> {

    private CustomerService customerService;
    private int entityCount;
    private int cacheSize;

    private final static String CACHE_KEY = CustomerListModel.class + "_cache";

    public CustomerListModel(Collection<? extends Customer> c, CustomerService customerService, int cacheSize) {
        super(c);
        this.customerService = customerService;
        this.cacheSize = cacheSize;
        entityCount = getSize();
    }

    @Override
    public void setPageSize(int pageSize) {
        this.cacheSize = pageSize;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer getElementAt(int index) {
        Map<Integer, Customer> cache = getCache();

        Customer targetCustomer = cache.get(index);
        if (targetCustomer == null) {
            //if cache doesn't contain target object, query a page size of data starting from the index
            int firstIndex;
            int lastIndex;
//            entityCount = getSize();
            if (cacheSize >= entityCount) {
                firstIndex = 0;
                lastIndex = entityCount;
            } else if (entityCount - cacheSize / 2 < index) {
                cacheSize = (int) (entityCount - index);
                firstIndex = index - cacheSize / 2;
                lastIndex = entityCount;
            } else if (index < cacheSize / 2) {
                firstIndex = 0;
                lastIndex = index + cacheSize / 2;
            } else {
                firstIndex = index - cacheSize / 2;
                lastIndex = index + cacheSize + 2;
            }

            List<Customer> pageResult = customerService.findCustomersFromIndexAndPageSize(firstIndex, lastIndex);
            int indexKey = firstIndex;
            for (Customer c : pageResult) {
                cache.put(indexKey, c);
                indexKey++;
            }
        } else {
            return targetCustomer;
        }

        targetCustomer = cache.get(index);
        if (targetCustomer == null) {
            //if we still cannot find the target object from database, there is inconsistency between memory and the database
            throw new RuntimeException("Element at index " + index + " cannot be found in the database.");
        } else {
            return targetCustomer;
        }
    }

    private Map<Integer, Customer> getCache() {
        Execution execution = Executions.getCurrent();
        //we only reuse this cache in one execution to avoid accessing detached objects.
        //our filter opens a session during a HTTP request
        Map<Integer, Customer> cache = (Map) execution.getAttribute(CACHE_KEY);
        if (cache == null) {
            cache = new HashMap<Integer, Customer>();
            execution.setAttribute(CACHE_KEY, cache);
        }
        return cache;
    }

    @Override
    public int getSize() {
        return customerService.getSize();
    }
}
