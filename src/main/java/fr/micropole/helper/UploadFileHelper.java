package fr.micropole.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileHelper {

    private final static String RESOURCES_PATH = "src/main/resources";

    private static final Logger LOGGER         = Logger.getLogger( UploadFileHelper.class );

    public static String[] uploadFile( String name, MultipartFile file ) {
        String[] message = new String[2];

        if ( StringUtils.isEmpty( name ) ) {

            name = file.getOriginalFilename();
        }
        if ( !file.isEmpty() ) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty( "catalina.home" );

                File dir = new File( rootPath + "/../../test/test/" + RESOURCES_PATH );
                if ( !dir.exists() )
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File( dir.getAbsolutePath()
                        + File.separator + name );
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream( serverFile ) );
                stream.write( bytes );
                stream.close();

                LOGGER.info( "Server File Location="
                        + serverFile.getAbsolutePath() );

                message[0] = "You successfully uploaded file=" + name;
                message[1] = dir.getAbsolutePath() + File.separator + name;
                return message;
            } catch ( Exception e ) {
                message[0] = "You failed to upload " + name + " => " + e.getMessage();
                return message;
            }
        } else {
            message[0] = "You failed to upload " + name + " because the file was empty.";
            return message;
        }
    }
}
