package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int length = 0;

    public int size() {
        return length;
    }

    public void clear() {
        Arrays.fill(storage, null);
        length = 0;
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            throw new NotExistStorageException("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }


    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (length == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            length++;
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[length - 1] = null;
            length--;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    public Resume[] getAll() {
        return copyOfRange(storage, 0, length);
    }

    protected abstract int findResumeIndex(String uuid);
}
