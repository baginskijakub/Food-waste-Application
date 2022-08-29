package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface extending interfaces ModelEmployee, ModelUser, UnnamedPropertyChangeSubject.
 * Allowing the model manager to implement only one interface
 */
public interface Model
    extends UnnamedPropertyChangeSubject, ModelEmployee, ModelUser
{

}
