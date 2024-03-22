package rpc.git.dms.exception;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String fileName){
        super("File not found with name:"+ fileName);
    }
}
