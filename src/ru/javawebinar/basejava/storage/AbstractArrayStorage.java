package ru.javawebinar.basejava.storage;

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

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("ERROR: This resume not exist");
            return null;
        }
    }

    public abstract void delete(String uuid);

    public abstract void save(Resume r);

    public Resume[] getAll() {
        return copyOfRange(storage, 0, length);
    }

    protected abstract int findResumeIndex(String uuid);
}
