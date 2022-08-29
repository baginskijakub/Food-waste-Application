package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

/**
 * Interface extending interfaces ClientUserModel, UnnamedPropertyChangeSubject.
 * Allowing the model manager to implement only one interface
 */
public interface Model
    extends UnnamedPropertyChangeSubject, ModelEmployee,
    ClientUserModel
{


}
