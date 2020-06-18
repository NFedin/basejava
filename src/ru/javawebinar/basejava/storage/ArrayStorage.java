package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOfRange;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int length = 0;

    public void clear() {
        Arrays.fill(storage, null);
        length = 0;
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }

    public void save(Resume r) {
        if (findResumeIndex(r.getUuid()) == -1) {
            if (length < STORAGE_LIMIT) {
                storage[length] = r;
                length++;
            } else {
                System.out.println("ERROR: overflow storage");
            }
        } else {
            System.out.println("ERROR: This resume exist");
        }
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

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            length--;
            arraycopy(storage, index + 1, storage, index, length - index);
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return copyOfRange(storage, 0, length);
    }

    public int size() {
        return length;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < length; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
