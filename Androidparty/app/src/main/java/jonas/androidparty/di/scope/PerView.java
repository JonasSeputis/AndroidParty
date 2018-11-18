package jonas.androidparty.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * class created by jonasseputis on 18/11/18
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerView {
}
