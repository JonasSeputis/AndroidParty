package jonas.androidparty.utils;

/**
 * class created by jonasseputis on 18/11/18
 */
public class TextUtils {

    public static boolean isTextIsEmptyOrNull(String text){
        if(text == null || text.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}
