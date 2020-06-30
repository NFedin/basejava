package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }

    @Override
    protected void insertElement(int index) {

    }

    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < length; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
