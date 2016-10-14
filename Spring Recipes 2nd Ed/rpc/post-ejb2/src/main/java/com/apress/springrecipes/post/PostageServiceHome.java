package com.apress.springrecipes.post;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface PostageServiceHome extends EJBHome {
    public PostageServiceRemote create() throws RemoteException, CreateException;
}
