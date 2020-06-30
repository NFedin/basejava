package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static java.lang.System.arraycopy;
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

    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            if (length < STORAGE_LIMIT) {
                insertElement(index);
                storage[length] = r;
                length++;
            } else {
                System.out.println("ERROR: overflow storage");
            }
        } else {
            System.out.println("ERROR: This resume exist");
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            length--;
            arraycopy(storage, index + 1, storage, index, length - index);
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }

    protected abstract void insertElement(int index);

    public Resume[] getAll() {
        return copyOfRange(storage, 0, length);
    }

    protected abstract int findResumeIndex(String uuid);
}
