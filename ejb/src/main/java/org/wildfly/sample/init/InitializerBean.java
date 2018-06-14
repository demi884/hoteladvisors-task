package org.wildfly.sample.init;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.sample.Utils;
import org.wildfly.sample.dao.ContactDao;
import org.wildfly.sample.dto.Contact;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.SplittableRandom;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class InitializerBean {
    private static final Logger log = LoggerFactory.getLogger(InitializerBean.class);
    private static final String STAGE = "development";
    private static final String CHANGELOG_FILE = "liquibase/db.changelog.xml";


    @Resource(lookup = "java:jboss/datasources/MySqlDS")
    private DataSource ds;

    @Inject
    private ContactDao contactDao;

    @PostConstruct
    protected void bootstrap() {
        log.info("Preparing datasource: {} ", ds);
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        try (Connection connection = ds.getConnection()) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

            Liquibase liquiBase = new Liquibase(CHANGELOG_FILE, resourceAccessor, db);
            liquiBase.update(STAGE);
        } catch (SQLException | LiquibaseException e) {
            log.error(StringUtils.EMPTY, e);
        }


        createMockData();
    }

    private static final int MOCK_CONTACTS_COUNT = 100;
    private void createMockData() {
        if (contactDao.findAll().size() == 0) {
            log.info("Crating mock data");
            for (int i = 0; i < MOCK_CONTACTS_COUNT; i++) {
                Contact randomContact = Utils.generateRandomContact();
                for (int j = 0; j < new SplittableRandom().nextInt(5); j++) {
                    randomContact.getAddressList().add(Utils.generateRandomAddress(randomContact.getId()));
                }
                contactDao.create(randomContact);
            }
        }
    }
}
