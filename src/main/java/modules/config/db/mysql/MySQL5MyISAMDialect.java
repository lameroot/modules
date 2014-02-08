package modules.config.db.mysql;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * User: lameroot
 * Date: 03.02.13
 * Time: 17:03
 * @see http://code.google.com/p/snofyre/source/browse/trunk/snomed-osgi/uk.nhs.cfh.dsp.snomed.persistence/src/main/java/uk/nhs/cfh/dsp/snomed/persistence/orm/MySQL5MyISAMDialect.java
 */
public class MySQL5MyISAMDialect extends MySQL5Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=MyISAM";
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }
}
