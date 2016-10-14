package com.apress.springrecipes.embedded;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;


public class EmbeddedDatabaseBuilderExample {
    public DataSource buildDataSource(EmbeddedDatabaseType edt, Resource... resources)
        throws IOException {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder = embeddedDatabaseBuilder.setType(edt);

        for (Resource resource : resources) {
            embeddedDatabaseBuilder.addScript(resource.getFile().getAbsolutePath());
        }

        return embeddedDatabaseBuilder.build();
    }

    public DataSource buildDerbyDataSource() throws IOException {
        return this.buildDataSource(EmbeddedDatabaseType.DERBY, new ClassPathResource("vehicle.sql"));
    }

    public static void main(String [] args ) throws Throwable {
        EmbeddedDatabaseBuilderExample embeddedDatabaseBuilderExample = new EmbeddedDatabaseBuilderExample();
        DataSource dataSource = embeddedDatabaseBuilderExample.buildDerbyDataSource() ;
        System.out.println( "Derby DB from Fluid Java API: " + dataSource.toString() ) ;
    }
}
