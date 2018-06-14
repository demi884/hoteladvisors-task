package org.wildfly.sample.ui.contact;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.sample.dao.ContactDao;
import org.wildfly.sample.dto.Contact;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class ContactViewModel {
    private static final Logger log = LoggerFactory.getLogger(ContactViewModel.class);

    private ContactDao contactDao;

    private static final String footerMessage = "A Total of %d Contact Items";
    private List<Contact> currentContactList;

    public ContactViewModel() {
        try {
            InitialContext context = new InitialContext();
            contactDao = (ContactDao) context.lookup("java:global/sample-app/ejb/ContactDao");
            currentContactList = contactDao.findAll();
        } catch (NamingException e) {
            log.error(StringUtils.EMPTY, e);
        }
    }

    public ListModel<Contact> getModel() {
        return new ListModelList<>(currentContactList);
    }

    public String getFooter() {
        return String.format(footerMessage, currentContactList.size());
    }
}
