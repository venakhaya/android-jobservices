package com.usermanager.app.services;

public interface IService<T> {
  T createUser(T user);
}
