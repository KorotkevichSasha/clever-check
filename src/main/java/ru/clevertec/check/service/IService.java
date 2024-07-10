package ru.clevertec.check.service;

public interface IService<T> {
    T load(String filePath);
}
