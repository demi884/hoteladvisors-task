package org.wildfly.sample.ui.address;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.sample.dao.AddressDao;
import org.wildfly.sample.dto.Address;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.stream.Collectors;

public class AddressFilterViewModel {
    private static final Logger log = LoggerFactory.getLogger(AddressFilterViewModel.class);

    private AddressDao addressDao;

    private static final String footerMessage = "A Total of %d Address Items";
    private final AddressFilter filter = new AddressFilter();
    private List<Address> currentAddressList;

    public AddressFilterViewModel() {
        try {
            InitialContext context = new InitialContext();
            addressDao = (AddressDao) context.lookup("java:global/sample-app/ejb/AddressDao");
            currentAddressList = addressDao.findAll();
        } catch (NamingException e) {
            log.error(StringUtils.EMPTY, e);
        }
    }

    public AddressFilter getFilter() {
        return filter;
    }

    public ListModel<Address> getModel() {
        return new ListModelList<>(currentAddressList);
    }

    public String getFooter() {
        return String.format(footerMessage, currentAddressList.size());
    }

    @Command
    @NotifyChange({"model", "footer"})
    public void changeFilter() {
        String city = StringUtils.trimToEmpty(filter.getCity());
        String contactId = StringUtils.trimToEmpty(filter.getContactId());

        currentAddressList = addressDao.findAll().stream()
                .filter(a -> a.getCity().contains(city) && a.getContactId().contains(contactId))
                .collect(Collectors.toList());
    }
}
