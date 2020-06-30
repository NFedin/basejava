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
        if (index < 0) {
            System.out.println("ERROR: This resume not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index > 0) {
            System.out.println("ERROR: This resume exist");
        } else if (length == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(r, index);
            length++;
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: This resume not exist");
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
