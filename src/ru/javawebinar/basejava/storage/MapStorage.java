package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage{

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected void doSave(Resume r, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}