package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.copyOfRange;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 1000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int length = 0;

    public int size() {
        return length;
    }

    public void clear() {
        Arrays.fill(storage, null);
        length = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }


    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if (length == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            length++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[length - 1] = null;
        length--;
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(copyOfRange(storage, 0, length));
    }

    protected abstract Integer getSearchKey(String uuid);

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }
}
