package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.lang.System.arraycopy;

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

    @Override
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

    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < length; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
