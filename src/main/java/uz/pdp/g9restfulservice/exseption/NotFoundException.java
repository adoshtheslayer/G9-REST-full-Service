package uz.pdp.g9restfulservice.exseption;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

   String message;

    public NotFoundException(String message) {
        super(message);
        this.message=message;
    }
}
