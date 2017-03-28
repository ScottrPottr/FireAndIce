package io.scottware.fireandice.presentation.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to designate an object whose instance
 * will be shared across the dependency graph of an Activity.
 * One 'per Activity'
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
