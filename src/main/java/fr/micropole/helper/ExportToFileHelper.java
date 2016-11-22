package fr.micropole.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import fr.micropole.pojo.CategorisationLibelle;

public class ExportToFileHelper {

    private static final char   DEFAULT_SEPARATOR = ';';
    private final static String RESOURCES_PATH    = "src/main/resources";

    public static void exportCategorisationLibelleDataToCSVFile( List<CategorisationLibelle> categorisationLibelles )
            throws IOException {

        List<String> values = new ArrayList<String>();
        String rootPath = System.getProperty( "catalina.home" );
        String path = rootPath + "/../../test/test/" + RESOURCES_PATH + "/categorisationLibelle.csv";

        FileWriter writer = new FileWriter( path );

        for ( CategorisationLibelle categorisationLibelle : categorisationLibelles ) {
            values.add( categorisationLibelle.getId().toString() );
            values.add( categorisationLibelle.getCategory().getName() );
            values.add( categorisationLibelle.getLibelle() );
            ExportToFileHelper.writeLine( writer, values );
        }

        writer.flush();
        writer.close();

    }

    public static void writeLine( Writer w, List<String> values ) throws IOException {

        // boolean first = true;

        // default customQuote is empty

        char separator = DEFAULT_SEPARATOR;

        StringBuilder sb = new StringBuilder();
        for ( String value : values ) {
            sb.append( value );
            // if ( !first ) {
            sb.append( separator );
            // }

            // first = false;
        }
        sb.append( "\n" );
        w.append( sb.toString() );

    }

}
