package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        storage[length] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {

    }

    @Override
    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < length; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
